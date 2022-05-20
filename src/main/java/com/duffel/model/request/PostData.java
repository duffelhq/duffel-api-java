package com.duffel.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PostData<T> {

    public PostData(T data) {
        this.data = data;
    }

    @JsonProperty("data")
    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
