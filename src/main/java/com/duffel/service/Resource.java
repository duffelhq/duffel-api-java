package com.duffel.service;

import com.duffel.DuffelApiClient;
import com.duffel.model.PagedData;
import com.duffel.net.ApiClient;

import java.util.ArrayList;
import java.util.List;

// T get request body type
// U get request paged body type
public class Resource<T, U> {

    protected final ApiClient apiClient;

    protected final String endpoint;

    protected Resource(ApiClient apiClient, String endpoint) {
        this.apiClient = apiClient;
        this.endpoint = endpoint;
    }

    protected U get(Class<U> clazz) {
        return apiClient.get(endpoint, clazz);
    }

    protected T getById(Class<T> clazz, String id) {
        return apiClient.get(endpoint + "/" + id, clazz);
    }

    protected T getById(Class<T> clazz, String id, String query) {
        return apiClient.get(endpoint + "/" + id + query, clazz);
    }

    protected U getPage(Class<U> clazz, String before, String after, Integer limit) {
        return getPage(clazz, "", before, after, limit);
    }

    protected U getPage(Class<U> clazz, String query, String before, String after, Integer limit) {
        String beforeParam = (before == null || before.isEmpty()) ? "" : "&before=" + before;
        String afterParam = (after == null || after.isEmpty()) ? "" : "&after=" + after;
        Integer limitParam = limit == null ? DuffelApiClient.DEFAULT_PAGE_LIMIT : limit;
        String queryParam = "?limit=" + limitParam + beforeParam + afterParam + query;
        return apiClient.get(endpoint + queryParam, clazz);
    }

    protected List<T> getAll(Class<U> clazz) {
        PagedData<T> page = (PagedData<T>) getPage(clazz, null, null, 200);
        List<T> response = new ArrayList<>(page.getData());
        while (page.getAfter() != null) {
            page = (PagedData<T>) getPage(clazz, null, page.getAfter(), 200);
            response.addAll(page.getData());
        }
        return response;
    }
}
