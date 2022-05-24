package com.duffel.model.response;

import com.duffel.model.Location;
import com.duffel.model.LocationType;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public class Slice {

    @JsonProperty("created_at")
    public LocalDateTime createdAt;

    @JsonProperty("origin")
    public Location origin;

    @JsonProperty("origin_type")
    public LocationType originType;

    @JsonProperty("destination")
    public Location destination;

    @JsonProperty("destination_type")
    public LocationType destinationType;

    @JsonProperty("departure_date")
    public String departureDate;

    @Override
    public String toString() {
        return "Slice{" +
                "createdAt=" + createdAt +
                ", origin=" + origin +
                ", originType=" + originType +
                ", destination=" + destination +
                ", destinationType=" + destinationType +
                ", departureDate=" + departureDate +
                '}';
    }
}
