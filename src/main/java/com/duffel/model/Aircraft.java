package com.duffel.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Aircraft {
    @JsonProperty("meta")
    private Meta meta;

    @JsonProperty("data")
    private List<Data> data;

    public static class Meta {
        @JsonProperty("limit")
        private Integer limit;

        @JsonProperty("before")
        private String before;

        @JsonProperty("after")
        private String after;

        @Override
        public String toString() {
            return "Meta{" +
                    "limit=" + limit +
                    ", before='" + before + '\'' +
                    ", after='" + after + '\'' +
                    '}';
        }
    }

    public static class Data {
        @JsonProperty("name")
        private String name;

        @JsonProperty("id")
        private String id;

        @JsonProperty("iata_code")
        private String iata_code;

        @Override
        public String toString() {
            return "Data{" +
                    "name='" + name + '\'' +
                    ", id='" + id + '\'' +
                    ", iata_code='" + iata_code + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "Aircraft{" +
                "meta=" + meta +
                ", data=" + data +
                '}';
    }
}
