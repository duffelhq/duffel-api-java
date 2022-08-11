package com.duffel.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@EqualsAndHashCode
@Getter
@Setter
@ToString
public class OrderChangeSlices {

    ///  <summary>
    ///  The slices that you wish to remove from your order
    ///  </summary>
    @JsonProperty("remove")
    private List<SliceId> remove;

    ///  <summary>
    ///  The search criteria for slices which you wish to add to your order
    ///  </summary>
    @JsonProperty("add")
    private List<ChangeSlice> add;

    @EqualsAndHashCode
    @Getter
    @Setter
    @ToString
    public static class SliceId {

        ///  <summary>
        ///  Duffel's unique identifier for the slice. It identifies the slice of an order (i.e. the same slice across
        ///  orders will have different ids.
        ///  Example: "sli_00009htYpSCXrwaB9Dn123"
        ///  </summary>
        @JsonProperty("slice_id")
        private String sliceId;

    }

    @EqualsAndHashCode
    @Getter
    @Setter
    @ToString
    public static class ChangeSlice {

        ///  <summary>
        ///  The cabin that the passengers want to travel in
        ///  Possible values: "first", "business", "premium_economy", or "economy"
        ///  </summary>
        @JsonProperty("cabin_class")
        private CabinClass cabinClass;

        ///  <summary>
        ///  The ISO 8601 date on which the passengers want to depart
        ///  Example: "2020-04-24"
        ///  </summary>
        @JsonProperty("departure_date")
        private String departureDate;

        ///  <summary>
        ///  The 3-letter IATA code for the city or airport where this slice ends
        ///  Example: "JFK"
        ///  </summary>
        @JsonProperty("destination")
        private String destination;

        ///  <summary>
        ///  The 3-letter IATA code for the city or airport where this slice starts
        ///  Example: "LHR"
        ///  </summary>
        @JsonProperty("origin")
        private String origin;

    }

}
