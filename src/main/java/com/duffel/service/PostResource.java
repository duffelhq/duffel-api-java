package com.duffel.service;

import com.duffel.model.request.PostData;
import com.duffel.net.ApiClient;

public class PostResource<T> extends Resource<T, T> {

    protected PostResource(ApiClient apiClient, String endpoint) {
        super(apiClient, endpoint);
    }

    protected T post(Class<T> clazz, PostData request) {
        return apiClient.post(endpoint, clazz, request);
    }

}
