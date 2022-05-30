package com.duffel.model.response;

import com.duffel.model.Data;
import com.duffel.model.Passenger;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.util.List;

public class OfferResponse extends Data<OfferResponse> {

    @JsonProperty("id")
    public String id;

    @JsonProperty("cabin_class")
    public String cabinClass;

    @JsonProperty("created_at")
    public LocalDateTime createdAt;

    @JsonProperty("live_mode")
    public boolean liveMode;

    @JsonProperty("offers")
    public List<Offer> offers;

    @JsonProperty("passengers")
    public List<Passenger> passengers;

    @JsonProperty("slices")
    public List<Slice> slices;

    @Override
    public String toString() {
        return "OfferResponse{" +
                "id='" + id + '\'' +
                ", cabinClass='" + cabinClass + '\'' +
                ", createdAt=" + createdAt +
                ", liveMode=" + liveMode +
                ", offers=" + offers +
                ", passengers=" + passengers +
                ", slices=" + slices +
                '}';
    }
}
