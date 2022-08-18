package com.duffel.service;

import com.duffel.DuffelApiClient;
import com.duffel.model.PagedData;
import com.duffel.net.ApiClient;

import java.util.ArrayList;
import java.util.List;

import static com.duffel.DuffelApiClient.DEFAULT_GET_ALL_PAGE_SIZE;

/*
Basic resource that supports a GET operation and paging of data
T get request body type
U get request paged body type
*/
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

    protected U get(Class<U> clazz, String params) {
        return apiClient.get(endpoint + params, clazz);
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

    // So, this is messy. But in the cases where we do a getAll, we need to parse it as a PagedData object here in
    // order to know the pointer to the next page. All the service implementations using this use U as a class
    // that implements PagedData<T>, but the type system doesn't guarantee this. Looked at adding a 3rd class
    // type T, U explicitly extending PagedData<V>, and V the paged data content type. This made it a whole lot more
    // complex to read though.
    // TODO: let's revisit this and attempt to make it explicit in the type system
    @SuppressWarnings("unchecked")
    protected List<T> getAll(Class<U> clazz) {
        PagedData<T> page = (PagedData<T>) getPage(clazz, null, null, DEFAULT_GET_ALL_PAGE_SIZE);
        List<T> response = new ArrayList<>(page.getData());
        while (page.getAfter() != null) {
            page = (PagedData<T>) getPage(clazz, null, page.getAfter(), DEFAULT_GET_ALL_PAGE_SIZE);
            response.addAll(page.getData());
        }
        return response;
    }
}
