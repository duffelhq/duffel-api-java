package com.duffel.model;

import com.duffel.Duffel;
import com.duffel.net.HttpClient;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.net.URI;
import java.net.URISyntaxException;

public abstract class PostData<T, U> {

    public static <T, U> U post(T postObject, String type, Class<U> collectionClazz) throws URISyntaxException {
        return new HttpClient(new URI(Duffel.API_ENDPOINT + "/" + type)).post(collectionClazz, postObject);
    }

    @JsonProperty("data")
    private T data;

    public void setData(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

}
