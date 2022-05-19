package com.duffel.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.net.URISyntaxException;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Aircraft extends Data<Aircraft> {

    public static AircraftCollection get() throws URISyntaxException {
        return get(Aircraft.class, AircraftCollection.class);
    }

    public static Aircraft getById(String id) throws URISyntaxException {
        return getById(Aircraft.class, id).getData();
    }

    @JsonProperty("name")
    private String name;

    @JsonProperty("id")
    private String id;

    @JsonProperty("iata_code")
    private String iataCode;

    @Override
    public String toString() {
        return "Aircraft{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", iataCode='" + iataCode + '\'' +
                '}';
    }
}
