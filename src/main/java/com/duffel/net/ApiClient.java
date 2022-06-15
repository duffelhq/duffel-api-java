package com.duffel.net;

import com.duffel.DuffelApiClient;
import com.duffel.exception.DuffelException;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.IOException;
import java.net.*;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;

public class ApiClient {

    private static final Logger logger = LogManager.getLogger(ApiClient.class);

    private static TrustManager[] trustAllCerts = new TrustManager[]{
            new X509TrustManager() {
                public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                    return null;
                }

                public void checkClientTrusted(
                        java.security.cert.X509Certificate[] certs, String authType) {
                }

                public void checkServerTrusted(
                        java.security.cert.X509Certificate[] certs, String authType) {
                }
            }
    };

    private HttpClient HTTP_CLIENT;

    private static final String APPLICATION_JSON = "application/json";
    private final Map<String, String> headers;
    private final String baseEndpoint;

    private final ObjectMapper objectMapper;

    public enum RequestMethod {
        GET,
        POST
    }

    public ApiClient(String apiKey, String baseEndpoint) {
        SSLContext sslContext;
        try {
            sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, trustAllCerts, new SecureRandom());
        } catch (NoSuchAlgorithmException | KeyManagementException e) {
            throw new RuntimeException(e);
        }

        HTTP_CLIENT = HttpClient.newBuilder()
//                .proxy(ProxySelector.of(InetSocketAddress.createUnresolved("localhost", 8080)))
//                .sslContext(sslContext)
                .build();

        this.baseEndpoint = baseEndpoint;
        this.headers = new HashMap<>();
        addAuthorizationHeader(apiKey);
        addBasicHeaders();
        objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
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
        headers.put("User-Agent", DuffelApiClient.USER_AGENT);
        headers.put("Duffel-Version", DuffelApiClient.API_VERSION);
        headers.put("Content-Type", APPLICATION_JSON);
    }

    public <T> T get(String endpoint, Class<T> clazz) {
        return executeCall(endpoint, RequestMethod.GET.name(), clazz, null);
    }

    public <T, O> T post(String endpoint, Class<T> clazz, O postObject) {
        return executeCall(endpoint, RequestMethod.POST.name(), clazz, postObject);
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
            logger.error("Failed to create API request", e);
            throw new RuntimeException(e);
        }

        HttpResponse<String> response;
        try {
            response = HTTP_CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            logger.error("Failed to send API request", e);
            throw new RuntimeException(e);
        }

        logger.info(response.body());

        try {
            if (response.statusCode() >= 200 && response.statusCode() < 300) {
                return objectMapper.readValue(response.body(), responseType);
            } else {
                logger.debug("Duffel returned an error with status code " + response.statusCode());
                throw objectMapper.readValue(response.body(), DuffelException.class);
            }
        } catch (JsonProcessingException e) {
            logger.error("Failed to deserialize the response body", e);
            throw new RuntimeException(e);
        }
    }
}
