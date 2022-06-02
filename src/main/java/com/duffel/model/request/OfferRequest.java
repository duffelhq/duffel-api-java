package com.duffel.model.request;

import com.duffel.model.Data;
import com.duffel.model.Passenger;
import com.duffel.model.response.Offer;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@ToString
public class OfferRequest extends Data<Offer> {

    @JsonProperty("max_connections")
    private int maxConnections;

    @JsonProperty("cabin_class")
    private String cabinClass;

    @JsonProperty("slices")
    private List<Slice> slices;

    @JsonProperty("passengers")
    private List<Passenger> passengers;

    @EqualsAndHashCode
    @Getter
    @Setter
    @ToString
    public static class Slice {
        @JsonProperty("origin")
        private String origin;

        @JsonProperty("destination")
        private String destination;

        @JsonProperty("departure_date")
        private String departureDate;
    }
}
