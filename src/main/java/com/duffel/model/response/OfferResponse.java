package com.duffel.model.response;

import com.duffel.model.Data;
import com.duffel.model.Passenger;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Getter
@ToString
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
