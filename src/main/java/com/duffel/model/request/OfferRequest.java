package com.duffel.model.request;

import com.duffel.model.Data;
import com.duffel.model.Passenger;
import com.duffel.model.response.Offer;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OfferRequest extends Data<Offer> {

    @JsonProperty("max_connections")
    public int maxConnections;

    @JsonProperty("cabin_class")
    public String cabinClass;

    @JsonProperty("slices")
    public List<Slice> slices;

    @JsonProperty("passengers")
    public List<Passenger> passengers;

    public static class Slice {
        @JsonProperty("origin")
        public String origin;

        @JsonProperty("destination")
        public String destination;

        @JsonProperty("departure_date")
        public String departureDate;
    }
}
