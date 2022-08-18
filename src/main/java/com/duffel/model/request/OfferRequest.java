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

@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@ToString
public class OfferRequest extends Data<Offer> {

    /// <summary>
    /// The maximum number of connections within any slice of the offer. For example 0 means a direct flight which will
    /// have a single segment within each slice and 1 means a maximum of two segments within each slice of the offer.
    /// </summary>
    @JsonProperty("max_connections")
    private int maxConnections;

    /// <summary>
    /// The cabin that the passengers want to travel in
    /// </summary>
    @JsonProperty("cabin_class")
    private CabinClass cabinClass;

    /// <summary>
    /// The slices that make up this offer request. One-way journeys can be expressed using one slice, whereas return
    /// trips will need two.
    /// </summary>
    @JsonProperty("slices")
    private List<Slice> slices;

    /// <summary>
    /// The passengers who want to travel. If you specify an age for a passenger, the type may differ for the same
    /// passenger in different offers due to airline's different rules. e.g. one airline may treat a 14 year old as an
    /// adult, and another as a young adult. You may only specify an age or a type – not both.
    /// </summary>
    @JsonProperty("passengers")
    private List<Passenger> passengers;

    @EqualsAndHashCode
    @Getter
    @Setter
    @ToString
    public static class Slice {
        /// <summary>
        /// The 3-letter IATA code for the city or airport where this slice starts
        /// </summary>
        @JsonProperty("origin")
        private String origin;

        /// <summary>
        /// The 3-letter IATA code for the city or airport where this slice ends
        /// </summary>
        @JsonProperty("destination")
        private String destination;

        /// <summary>
        /// The ISO 8601 date on which the passengers want to depart
        /// </summary>
        @JsonProperty("departure_date")
        private String departureDate;
    }
}
