package com.duffel.model.response;

import com.duffel.model.Data;
import com.duffel.model.Passenger;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Set of offers.
 */
@EqualsAndHashCode(callSuper = true)
@Getter
@ToString
public class OfferResponse extends Data<OfferResponse> {

    /**
     * Duffel's unique identifier for the offer request
     * Example: "orq_00009hjdomFOCJyxHG7k7k"
     */
    @JsonProperty("id")
    private String id;

    /**
     * Client key for use authenticating with
     * <a href="https://www.duffel.com/docs/ancillaries-component">Duffel Ancillaries Component</a>
     */
    @JsonProperty("client_key")
    private String clientKey;

    /**
     * The cabin that the passengers want to travel in
     * Possible values: "first", "business", "premium_economy", or "economy"
     */
    @JsonProperty("cabin_class")
    private String cabinClass;

    /**
     * The ISO 8601 datetime at which the offer request was created
     * Example: "2020-02-12T15:21:01.927Z"
     */
    @JsonProperty("created_at")
    private LocalDateTime createdAt;

    /**
     * Whether the offer request was created in live mode. This field will be set to true if the offer request was
     * created in live mode, or false if it was created in test mode.
     * Example: false
     */
    @JsonProperty("live_mode")
    private boolean liveMode;

    /**
     * The offers returned by the airlines
     */
    @JsonProperty("offers")
    private List<Offer> offers;

    /**
     * The passengers who want to travel
     */
    @JsonProperty("passengers")
    private List<Passenger> passengers;

    /**
     * The slices that make up this offer request. One-way journeys can be expressed using one slice, whereas return
     * trips will need two.
     */
    @JsonProperty("slices")
    private List<Slice> slices;

}
