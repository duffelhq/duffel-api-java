package com.duffel.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OfferResponse extends Data<OfferResponse> {

    @JsonProperty("id")
    private String id;

    @JsonProperty("cabin_class")
    private String cabinClass;

    @JsonProperty("created_at")
    private LocalDateTime createdAt;

    @JsonProperty("live_mode")
    private boolean liveMode;

    @JsonProperty("offers")
    private List<Offer> offers;

    @JsonProperty("passengers")
    private List<Passenger> passengers;

    @JsonProperty("slices")
    private List<Slice> slices;
}
