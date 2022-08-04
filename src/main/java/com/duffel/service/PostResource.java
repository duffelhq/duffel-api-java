package com.duffel.service;

import com.duffel.model.PagedData;
import com.duffel.model.request.PostData;
import com.duffel.net.ApiClient;

/*
Basic resource that supports GET, POST, and UPDATE operations and paging of data
T get/post request body type
U get/post request paged body type
V get/post request paged body content type
*/
public class PostResource<T, U extends PagedData<V>, V> extends Resource<T, U, V> {

    protected PostResource(ApiClient apiClient, String endpoint) {
        super(apiClient, endpoint);
    }

    protected T post(Class<T> clazz, PostData<?> request) {
        return apiClient.post(endpoint, clazz, request);
    }

    protected T post(Class<T> clazz, String id, PostData<?> request) {
        return apiClient.post(endpoint + "/" + id, clazz, request);
    }

    protected T update(Class<T> clazz, String id, PostData<?> request) {
        return apiClient.patch(endpoint + "/" + id, clazz, request);
    }

}
