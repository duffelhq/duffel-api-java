package com.duffel.service;

import com.duffel.DuffelApiClient;
import com.duffel.net.HttpClient;
import com.google.common.base.Strings;

import java.net.URI;
import java.net.URISyntaxException;

public class Resource<T, U> {

    protected final HttpClient httpClient;

    protected final String endpoint;

    protected Resource(HttpClient httpClient, String endpoint) {
        this.httpClient = httpClient;
        this.endpoint = endpoint;
    }

    protected U get(Class<U> clazz) throws URISyntaxException {
        return httpClient.get(new URI(DuffelApiClient.API_ENDPOINT + endpoint), clazz);
    }

    protected T getById(Class<T> clazz, String id) throws URISyntaxException {
        return httpClient.get(new URI(DuffelApiClient.API_ENDPOINT + endpoint + "/" + id), clazz);
    }

    protected U page(Class<U> clazz, String before, String after, Integer limit) throws URISyntaxException {
        return page(clazz, "", before, after, limit);
    }

    protected U page(Class<U> clazz, String selector, String before, String after, Integer limit) throws URISyntaxException {
        String beforeParam = Strings.isNullOrEmpty(before) ? "" : "&before=" + before;
        String afterParam = Strings.isNullOrEmpty(after) ? "" : "&after=" + after;
        Integer limitParam = limit == null ? DuffelApiClient.DEFAULT_PAGE_LIMIT : limit;
        String queryParam = "?limit=" + limitParam + beforeParam + afterParam + selector;
        return httpClient.get(new URI(DuffelApiClient.API_ENDPOINT + endpoint + queryParam), clazz);
    }
}
