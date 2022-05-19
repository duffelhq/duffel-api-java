package com.duffel.net;

import com.duffel.Duffel;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.io.CharStreams;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

public class HttpClient {

    private static final Logger logger = LogManager.getLogger(HttpClient.class);
    private static final String APPLICATION_JSON = "application/json";

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
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    private HttpURLConnection setupConnection(URI uri, String httpMethod) throws IOException {
        TempAllCerts.install();
        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("localhost", 8080));
        HttpURLConnection connection = (HttpURLConnection) uri.toURL().openConnection(proxy);
        connection.setRequestMethod(httpMethod);
        headers.forEach(connection::setRequestProperty);
        return connection;
    }

    private <O> void setupPostBody(HttpURLConnection connection, O postObject) throws IOException {
        connection.setDoOutput(true);
        try (OutputStream postStream = connection.getOutputStream()) {
            objectMapper.writeValue(postStream, postObject);
            logger.info(objectMapper.writeValueAsString(postObject));
        }
    }

    private void setAuthorizationHeader() {
        headers.put("Authorization", "Bearer " + Duffel.apiKey);
    }

    private void setBasicHeaders() {
        headers.put("Accept", APPLICATION_JSON);
        headers.put("User-Agent", Duffel.USER_AGENT);
        headers.put("Duffel-Version", Duffel.API_VERSION);
        headers.put("Content-Type", APPLICATION_JSON);
    }

    public <T> T get(Class<T> clazz) {
        return executeCall(RequestMethod.GET.name(), clazz, null);
    }

    public <T, O> T post(Class<T> clazz, O postObject) {
        return executeCall(RequestMethod.POST.name(), clazz, postObject);
    }

    private <T, O> T executeCall(String httpMethod, Class<T> responseType, O postObject ) {
        HttpURLConnection connection;
        T response = null;
        try {
            connection = setupConnection(uri, httpMethod);
            if (postObject != null) {
                setupPostBody(connection, postObject);
            }
            connection.connect();

            int responseCode = connection.getResponseCode();
//            logger.info("Got response " + responseCode + " for " + uri);
            String responseBody = CharStreams.toString(new InputStreamReader(connection.getInputStream()));
            response = objectMapper.readValue(responseBody, responseType);
//            logger.info(response);
        } catch (IOException e) {
            logger.error("Fail", e);
        }
        return response;
    }
}
