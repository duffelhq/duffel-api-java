package com.duffel.net;

import com.duffel.DuffelApiClient;
import com.duffel.exception.DuffelException;
import com.duffel.exception.RateLimitException;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.GZIPInputStream;

public class ApiClient {

    private static final Logger LOG = LogManager.getLogger(ApiClient.class);

    private final HttpClient HTTP_CLIENT;

    private static final String APPLICATION_JSON = "application/json";
    private static final String GZIP = "gzip";
    private static final String CONTENT_ENCODING_HEADER = "content-encoding";
    private static final String RATE_LIMIT_HEADER = "ratelimit-reset";
    private final Map<String, String> headers;
    private final String baseEndpoint;

    private final ObjectMapper objectMapper;

    public enum RequestMethod {
        GET,
        PATCH,
        POST
    }

    public ApiClient(String apiKey, String baseEndpoint) {
        HTTP_CLIENT = HttpClient.newBuilder().build();

        this.baseEndpoint = baseEndpoint;
        this.headers = new HashMap<>();
        addAuthorizationHeader(apiKey);
        addBasicHeaders();

        this.objectMapper = setupObjectMapper();
    }

    private ObjectMapper setupObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        objectMapper.configure(JsonGenerator.Feature.WRITE_BIGDECIMAL_AS_PLAIN, true);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.configOverride(BigDecimal.class).setFormat(JsonFormat.Value.forShape(JsonFormat.Shape.STRING));

        return objectMapper;
    }

    private HttpRequest prepareRequest(String endpoint, String httpMethod, String requestBody) throws IOException, URISyntaxException {
        HttpRequest.Builder requestBuilder = HttpRequest.newBuilder()
                .uri(new URI(baseEndpoint + endpoint))
                .method(httpMethod, requestBody == null ? HttpRequest.BodyPublishers.noBody() : HttpRequest.BodyPublishers.ofString(requestBody));

        headers.forEach(requestBuilder::setHeader);

        return requestBuilder.build();
    }

    private void addAuthorizationHeader(String apiKey) {
        headers.put("Authorization", "Bearer " + apiKey);
    }

    private void addBasicHeaders() {
        headers.put("Accept", APPLICATION_JSON);
        headers.put("Accept-Encoding", GZIP);
        headers.put("Content-Type", APPLICATION_JSON);
        headers.put("Duffel-Version", DuffelApiClient.API_VERSION);
        headers.put("User-Agent", DuffelApiClient.USER_AGENT);
    }

    public <T> T get(String endpoint, Class<T> clazz) {
        return executeCall(endpoint, RequestMethod.GET.name(), clazz, null);
    }

    public <T, O> T post(String endpoint, Class<T> clazz, O postObject) {
        return executeCall(endpoint, RequestMethod.POST.name(), clazz, postObject);
    }

    public <T, O> T patch(String endpoint, Class<T> clazz, O postObject) {
        return executeCall(endpoint, RequestMethod.PATCH.name(), clazz, postObject);
    }

    private <T, O> T executeCall(String endpoint, String httpMethod, Class<T> responseType, O postObject) throws DuffelException {
        String requestBody = null;
        if (postObject != null) {
            try {
                requestBody = objectMapper.writeValueAsString(postObject);
            } catch (JsonProcessingException e) {
                throw new RuntimeException("Failed to serialize request body to JSON");
            }
        }

        HttpRequest request;
        try {
            request = prepareRequest(endpoint, httpMethod, requestBody);
        } catch (IOException | URISyntaxException e) {
            LOG.error("Failed to create API request", e);
            throw new RuntimeException(e);
        }

        HttpResponse<byte[]> response;
        try {
            response = HTTP_CLIENT.send(request, HttpResponse.BodyHandlers.ofByteArray());
            LOG.debug("📝 Call to {} has trace ID {}", endpoint, response.headers().firstValue("x-request-id").orElse("❌ NOT FOUND"));
        } catch (IOException | InterruptedException e) {
            LOG.error("Failed to send API request", e);
            throw new RuntimeException(e);
        }

        String body;
        if (GZIP.equals(response.headers().firstValue(CONTENT_ENCODING_HEADER).orElse(""))) {
            try (GZIPInputStream stream = new GZIPInputStream(new ByteArrayInputStream(response.body()))) {
                body = new String(stream.readAllBytes(), StandardCharsets.UTF_8);
            } catch (IOException e) {
                LOG.error("Failed to decompress response body", e);
                throw new RuntimeException(e);
            }
        } else {
            body = new String(response.body(), StandardCharsets.UTF_8);
        }

        try {
            if (response.statusCode() >= 200 && response.statusCode() < 300) {
                if (body.isBlank() || "{}".equals(body)) {
                    LOG.debug("Duffel returned an empty response body, returning null");
                    return null;
                } else {
                    return objectMapper.readValue(body, responseType);
                }
            } else if (response.statusCode() == 429) {
                LocalDateTime rateLimitReset = (response.headers().firstValue(RATE_LIMIT_HEADER).isPresent()) ?
                        LocalDateTime.parse(response.headers().firstValue(RATE_LIMIT_HEADER).get(), DateTimeFormatter.RFC_1123_DATE_TIME)
                        : null;
                LOG.debug("Duffel returned an rate limit response with a reset of {}", rateLimitReset);
                RateLimitException exception = objectMapper.readValue(body, RateLimitException.class);
                exception.setRateLimitReset(rateLimitReset);
                throw exception;
            } else {
                LOG.debug("Duffel returned an error with status code {}", response.statusCode());
                throw objectMapper.readValue(body, DuffelException.class);
            }
        } catch (JsonProcessingException e) {
            LOG.error("Failed to deserialize the response body", e);
            throw new RuntimeException(e);
        }
    }
}
