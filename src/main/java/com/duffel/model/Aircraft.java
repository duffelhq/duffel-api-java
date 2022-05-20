package com.duffel.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Aircraft extends Data<Aircraft> {

    @JsonProperty("name")
    public String name;

    @JsonProperty("id")
    public String id;

    @JsonProperty("iata_code")
    public String iataCode;

    @Override
    public String toString() {
        return "Aircraft{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", iataCode='" + iataCode + '\'' +
                '}';
    }
}
