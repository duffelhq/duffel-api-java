package com.duffel.model;

import com.duffel.Duffel;
import com.duffel.net.HttpClient;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Locale;

public abstract class Data<T> {

    public static <T, U> U get(Class<T> clazz, Class<U> collectionClazz) throws URISyntaxException {
        return new HttpClient(new URI(Duffel.API_ENDPOINT + "/" + clazz.getSimpleName().toLowerCase(Locale.ROOT))).get(collectionClazz);
    }

    public static <T> T getById(Class<T> clazz, String id) throws URISyntaxException {
        return new HttpClient(new URI(Duffel.API_ENDPOINT + "/" + clazz.getSimpleName().toLowerCase(Locale.ROOT) + "/"+ id)).get(clazz);
    }

    @JsonProperty("data")
    private T data;

    public T getData() {
        return data;
    }
}
