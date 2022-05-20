package com.duffel.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class PagedData<T> {
    @JsonProperty("meta")
    public PagedDataMeta meta;

    @JsonProperty("data")
    public List<T> data;

    public Integer getLimit() {
        return meta.getLimit();
    }

    public String getBefore() {
        return meta.getBefore();
    }

    public String getAfter() {
        return meta.getAfter();
    }

    public List<T> getData() {
        return data;
    }

    public PagedDataMeta getMeta() {
        return meta;
    }

    @Override
    public String toString() {
        return "PagedData{" +
                "meta=" + meta +
                ", data=" + data +
                '}';
    }

    static class PagedDataMeta {
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

}
