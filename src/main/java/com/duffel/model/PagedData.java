package com.duffel.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@EqualsAndHashCode
@Getter
@ToString
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

    public static class PagedDataMeta {
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

    }

}
