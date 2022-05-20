package com.duffel.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class Data<T> {

    @JsonProperty("data")
    public T data;

    public T getData() {
        return data;
    }

}
