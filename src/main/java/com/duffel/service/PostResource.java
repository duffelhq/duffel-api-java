package com.duffel.service;

import com.duffel.model.request.PostData;
import com.duffel.net.HttpClient;

public class PostResource<T> extends Resource<T, T> {

    protected PostResource(HttpClient httpClient, String endpoint) {
        super(httpClient, endpoint);
    }

    protected T post(Class<T> clazz, PostData request) {
        return httpClient.post(endpoint, clazz, request);
    }

}
