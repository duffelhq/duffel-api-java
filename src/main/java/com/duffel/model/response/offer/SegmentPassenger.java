package com.duffel.model.response.offer;

import com.duffel.model.CabinClass;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class SegmentPassenger {

    ///  <summary>
    ///  The identifier for the passenger.
    ///  You may have specified this ID yourself when creating the offer request, or otherwise, Duffel will have generated its own random ID.
    ///  </summary>
    @JsonProperty("passenger_id")
    public String passengerId;

    ///  <summary>
    ///  The airline's alphanumeric code for the fare that the passenger is using to travel.
    ///  Where this is null, it means that either the fare basis code is not available or the airline does not use fare basis codes.
    ///  </summary>
    @JsonProperty("fare_basis_code")
    public String fareBasisCode;

    ///  <summary>
    ///  The name that the marketing carrier uses to market this cabin class
    ///  </summary>
    ///  <example>"Economy Basic"</example>
    @JsonProperty("cabin_class_marketing_name")
    public String cabinClassMarketingName;

    ///  <summary>
    ///  The cabin class that the passenger will travel in on this segment
    ///  Possible values: "first", "business", "premium_economy", or "economy"
    ///  </summary>
    @JsonProperty("cabin_class")
    public CabinClass cabinClass;

    ///  <summary>
    ///  The baggage allowances for the passenger on this segment included in the offer.
    ///  Some airlines may allow additional baggage to be booked as a service - see the offer's available_services.
    ///  </summary>
    ///  <remarks>This property is returned by the API as "baggages"</remarks>
    @JsonProperty("baggages")
    public List<BaggageAllowance> baggageAllowances;

    @Override
    public String toString() {
        return "SegmentPassenger{" +
                "passengerId='" + passengerId + '\'' +
                ", fareBasisCode='" + fareBasisCode + '\'' +
                ", cabinClassMarketingName='" + cabinClassMarketingName + '\'' +
                ", cabinClass=" + cabinClass +
                ", baggageAllowances=" + baggageAllowances +
                '}';
    }
}
