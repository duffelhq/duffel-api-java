package com.duffel.service;

import com.duffel.DuffelApiClient;
import com.duffel.net.HttpClient;
import com.google.common.base.Strings;

public class Resource<T, U> {

    protected final HttpClient httpClient;

    protected final String endpoint;

    protected Resource(HttpClient httpClient, String endpoint) {
        this.httpClient = httpClient;
        this.endpoint = endpoint;
    }

    protected U get(Class<U> clazz) {
        return httpClient.get(endpoint, clazz);
    }

    protected T getById(Class<T> clazz, String id) {
        return httpClient.get(endpoint + "/" + id, clazz);
    }

    protected U page(Class<U> clazz, String before, String after, Integer limit) {
        return page(clazz, "", before, after, limit);
    }

    protected U page(Class<U> clazz, String selector, String before, String after, Integer limit) {
        String beforeParam = Strings.isNullOrEmpty(before) ? "" : "&before=" + before;
        String afterParam = Strings.isNullOrEmpty(after) ? "" : "&after=" + after;
        Integer limitParam = limit == null ? DuffelApiClient.DEFAULT_PAGE_LIMIT : limit;
        String queryParam = "?limit=" + limitParam + beforeParam + afterParam + selector;
        return httpClient.get(endpoint + queryParam, clazz);
    }
}
