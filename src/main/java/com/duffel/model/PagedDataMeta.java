package com.duffel.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PagedDataMeta {
    @JsonProperty("limit")
    private Integer limit;

    @JsonProperty("before")
    private String before;

    @JsonProperty("after")
    private String after;

    public Integer getLimit() {
        return limit;
    }

    public String getBefore() {
        return before;
    }

    public String getAfter() {
        return after;
    }

    @Override
    public String toString() {
        return "Meta{" +
                "limit=" + limit +
                ", before='" + before + '\'' +
                ", after='" + after + '\'' +
                '}';
    }
}
