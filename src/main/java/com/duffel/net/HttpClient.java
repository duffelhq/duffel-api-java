package com.duffel.net;

import com.duffel.Duffel;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.io.CharStreams;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

public class HttpClient {

    private static final Logger logger = LogManager.getLogger(HttpClient.class);

    private final URI uri;
    private final Map<String, String> headers;

    private final ObjectMapper objectMapper;

    public enum RequestMethod {
        GET,
        POST
    }

    public HttpClient(URI uri) {
        this(uri, new HashMap<>());
    }

    public HttpClient(URI uri, Map<String, String> headers) {
        this.uri = uri;
        this.headers = headers;
        setAuthorizationHeader();
        setBasicHeaders();
        objectMapper = new ObjectMapper();
    }

    private HttpURLConnection setupConnection(URI uri, String httpMethod) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) uri.toURL().openConnection();
        connection.setRequestMethod(httpMethod);
        headers.forEach(connection::setRequestProperty);
        return connection;
    }

    private void setAuthorizationHeader() {
        headers.put("Authorization", "Bearer " + Duffel.apiKey);
    }

    private void setBasicHeaders() {
        headers.put("Accept", "application/json");
        headers.put("User-Agent", Duffel.USER_AGENT);
        headers.put("Duffel-Version", Duffel.API_VERSION);
    }

    public <T> T get(Class<T> clazz) {
        return executeCall(RequestMethod.GET.name(), clazz);
    }

    private <T> T executeCall(String httpMethod, Class<T> responseType) {
        HttpURLConnection connection;
        T response = null;
        try {
            connection = setupConnection(uri, httpMethod);
            connection.connect();

            int responseCode = connection.getResponseCode();
            logger.info("Got response " + responseCode + " for " + uri);
            String responseBody = CharStreams.toString(new InputStreamReader(connection.getInputStream()));
            response = objectMapper.readValue(responseBody, responseType);
            logger.info(response);
        } catch (IOException e) {
            logger.error("Fail", e);
        }
        return response;
    }
}
