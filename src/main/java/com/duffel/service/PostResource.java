package com.duffel.service;

import com.duffel.DuffelApiClient;
import com.duffel.model.request.PostData;
import com.duffel.net.HttpClient;

import java.net.URI;
import java.net.URISyntaxException;

public class PostResource<T> extends Resource<T, T> {

    protected PostResource(HttpClient httpClient, String endpoint) {
        super(httpClient, endpoint);
    }

    protected T post(Class<T> clazz, PostData request) throws URISyntaxException {
        return httpClient.post(new URI(DuffelApiClient.API_ENDPOINT + endpoint), clazz, request);
    }

}
