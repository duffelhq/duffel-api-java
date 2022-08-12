package com.duffel.service;

import com.duffel.model.request.PostData;
import com.duffel.net.ApiClient;

/*
Basic resource that supports GET, POST, and UPDATE operations and paging of data
T get/post request body type
U get/post request paged body type
*/
public class PostResource<T, U> extends Resource<T, U> {

    protected PostResource(ApiClient apiClient, String endpoint) {
        super(apiClient, endpoint);
    }

    protected T post(Class<T> clazz, PostData<?> request) {
        return apiClient.post(endpoint, clazz, request);
    }

    protected T post(Class<T> clazz, String id, PostData<?> request) {
        return apiClient.post(endpoint + "/" + id, clazz, request);
    }

    protected T patch(Class<T> clazz, String id, PostData<?> request) {
        return apiClient.patch(endpoint + "/" + id, clazz, request);
    }

    protected T update(Class<T> clazz, String id, PostData<?> request) {
        return apiClient.patch(endpoint + "/" + id, clazz, request);
    }

}
