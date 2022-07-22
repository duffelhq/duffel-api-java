package com.duffel.service;

import com.duffel.model.request.PostData;
import com.duffel.net.ApiClient;

// T post request body type
// U post request paged body type
public class PostResource<T, U> extends Resource<T, U> {

    protected PostResource(ApiClient apiClient, String endpoint) {
        super(apiClient, endpoint);
    }

    protected T post(Class<T> clazz, PostData request) {
        return apiClient.post(endpoint, clazz, request);
    }

    protected T post(Class<T> clazz, String id, PostData request) {
        return apiClient.post(endpoint + "/" + id, clazz, request);
    }

    protected T update(Class<T> clazz, String id, PostData request) {
        return apiClient.patch(endpoint + "/" + id, clazz, request);
    }

}
