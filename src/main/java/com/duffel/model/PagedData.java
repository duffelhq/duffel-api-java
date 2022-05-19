package com.duffel.model;

import com.duffel.Duffel;
import com.duffel.net.HttpClient;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Locale;

public abstract class PagedData<T, U> {

    public static <U> U get(Class<U> clazz, String type, String after) throws URISyntaxException {
        return new HttpClient(new URI(Duffel.API_ENDPOINT + "/" + type.toLowerCase(Locale.ROOT) + "?after=" + after)).get(clazz);
    }

    public boolean hasNext() {
        return meta.getAfter() != null;
    }

    public U next(Class<U> clazz, String type) throws URISyntaxException {
        if (hasNext()) {
            return get(clazz, type, meta.getAfter());
        } else {
            return null;
        }
    }

    public boolean hasBefore() {
        return meta.getBefore() != null;
    }

    public U before(Class<U> clazz, String type) throws URISyntaxException {
        if (hasBefore()) {
            return get(clazz, type, meta.getBefore());
        } else {
            return null;
        }
    }

    @JsonProperty("meta")
    public PagedDataMeta meta;

    @JsonProperty("data")
    private List<T> data;

    public PagedDataMeta getMeta() {
        return meta;
    }

    public List<T> getData() {
        return data;
    }

}
