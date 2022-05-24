package com.duffel.net;

import com.duffel.DuffelApiClient;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.io.CharStreams;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

public class HttpClient {

    private static final Logger logger = LogManager.getLogger(HttpClient.class);
    private static final String APPLICATION_JSON = "application/json";
    private final Map<String, String> headers;
    private final String baseEndpoint;

    private final ObjectMapper objectMapper;

    public enum RequestMethod {
        GET,
        POST
    }

    public HttpClient(String apiKey, String baseEndpoint) {
        this(apiKey, baseEndpoint, new HashMap<>());
    }

    public HttpClient(String apiKey, String baseEndpoint, Map<String, String> headers) {
        this.baseEndpoint = baseEndpoint;
        this.headers = headers;
        setAuthorizationHeader(apiKey);
        setBasicHeaders();
        objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    private HttpURLConnection setupConnection(String endpoint, String httpMethod) throws IOException, URISyntaxException {
//        TempAllCerts.install();
//        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("localhost", 8080));

        URI uri = new URI(baseEndpoint + endpoint);

        HttpURLConnection connection = (HttpURLConnection) uri.toURL().openConnection();
        connection.setRequestMethod(httpMethod);
        headers.forEach(connection::setRequestProperty);
        return connection;
    }

    private <O> void setupPostBody(HttpURLConnection connection, O postObject) throws IOException, DatabindException {
        connection.setDoOutput(true);
        try (OutputStream postStream = connection.getOutputStream()) {
            objectMapper.writeValue(postStream, postObject);
            logger.info(objectMapper.writeValueAsString(postObject));
        }
    }

    private void setAuthorizationHeader(String apiKey) {
        headers.put("Authorization", "Bearer " + apiKey);
    }

    private void setBasicHeaders() {
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

    private <T, O> T executeCall(String endpoint, String httpMethod, Class<T> responseType, O postObject ) {
        HttpURLConnection connection;
        T response = null;
        try {
            connection = setupConnection(endpoint, httpMethod);
            if (postObject != null) {
                setupPostBody(connection, postObject);
            }
            connection.connect();

            int responseCode = connection.getResponseCode();
            logger.info(responseCode);
            String responseBody = CharStreams.toString(new InputStreamReader(connection.getInputStream()));
            response = objectMapper.readValue(responseBody, responseType);
        } catch (IOException | URISyntaxException e) {
            logger.error("Fail", e);
        }
        return response;
    }
}
