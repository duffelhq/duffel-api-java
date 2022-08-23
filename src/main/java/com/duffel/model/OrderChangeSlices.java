package com.duffel.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * Information around an order change.
 */
@EqualsAndHashCode
@Getter
@Setter
@ToString
public class OrderChangeSlices {

    /**
     * The slices that you wish to remove from your order
     */
    @JsonProperty("remove")
    private List<SliceId> remove;

    /**
     * The search criteria for slices which you wish to add to your order
     */
    @JsonProperty("add")
    private List<ChangeSlice> add;

    /**
     * Slice unique ID.
     */
    @EqualsAndHashCode
    @Getter
    @Setter
    @ToString
    public static class SliceId {

        /**
         * Duffel's unique identifier for the slice. It identifies the slice of an order (i.e. the same slice across
         * orders will have different ids.)
         * Example: "sli_00009htYpSCXrwaB9Dn123"
         */
        @JsonProperty("slice_id")
        private String sliceId;

    }

    /**
     * Details of the slice change.
     */
    @EqualsAndHashCode
    @Getter
    @Setter
    @ToString
    public static class ChangeSlice {

        /**
         * The cabin that the passengers want to travel in
         * Possible values: "first", "business", "premium_economy", or "economy"
         */
        @JsonProperty("cabin_class")
        private CabinClass cabinClass;

        /**
         * The ISO 8601 date on which the passengers want to depart
         * Example: "2020-04-24"
         */
        @JsonProperty("departure_date")
        private String departureDate;

        /**
         * The 3-letter IATA code for the city or airport where this slice ends
         * Example: "JFK"
         */
        @JsonProperty("destination")
        private String destination;

        /**
         * The 3-letter IATA code for the city or airport where this slice starts
         * Example: "LHR"
         */
        @JsonProperty("origin")
        private String origin;

    }

}
