package com.duffel.model.request;

import com.duffel.model.CabinClass;
import com.duffel.model.Data;
import com.duffel.model.Passenger;
import com.duffel.model.response.Offer;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.Map;

/**
 * Offer request body.
 */
@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@ToString
public class OfferRequest extends Data<Offer> {

    /**
     * The maximum number of connections within any slice of the offer. For example 0 means a direct flight which will
     * have a single segment within each slice and 1 means a maximum of two segments within each slice of the offer.
     */
    @JsonProperty("max_connections")
    private int maxConnections;

    /**
     * The cabin that the passengers want to travel in
     */
    @JsonProperty("cabin_class")
    private CabinClass cabinClass;

    /**
     * The slices that make up this offer request. One-way journeys can be expressed using one slice, whereas return
     * trips will need two.
     */
    @JsonProperty("slices")
    private List<Slice> slices;

    /**
     * The passengers who want to travel. If you specify an age for a passenger, the type may differ for the same
     * passenger in different offers due to airline's different rules. e.g. one airline may treat a 14-year-old as an
     * adult, and another as a young adult. You may only specify an age or a type – not both.
     */
    @JsonProperty("passengers")
    private List<Passenger> passengers;

    /**
     * The private fare codes for this Offer Request. You can pass in multiple airlines with their specific private fare
     * codes. The key is the airline's IATA code that provided the private fare code. The corporate_code is provided to
     * you by the airline and the tracking_reference is to identify your business by the airlines.
     * Example: {"QF":[{"corporate_code":"FLX53","tracking_reference":"ABN:2345678"}],"UA":[{"corporate_code":"1234"}]}
     */
    @JsonProperty("private_fares")
    private Map<String, List<PrivateFare>> privateFares;

    /**
     * Slice information.
     */
    @EqualsAndHashCode
    @Getter
    @Setter
    @ToString
    public static class Slice {
        /**
         * The 3-letter IATA code for the city or airport where this slice starts
         */
        @JsonProperty("origin")
        private String origin;

        /**
         * The 3-letter IATA code for the city or airport where this slice ends
         */
        @JsonProperty("destination")
        private String destination;

        /**
         * The ISO 8601 date on which the passengers want to depart
         */
        @JsonProperty("departure_date")
        private String departureDate;
    }
}
