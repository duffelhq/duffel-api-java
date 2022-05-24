package com.duffel.model.response.offer;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BaggageAllowance {

    ///  <summary>
    ///  The number of this type of bag allowed on the segment.
    ///  Note that this can currently be 0 in some cases.
    ///  </summary>
    @JsonProperty("quantity")
    public Integer quantity;

    ///  <summary>
    ///  The type of the baggage allowance. Possible values defined in <see cref="BaggageType"/>
    ///  </summary>
    @JsonProperty("type")
    public BaggageType baggageType;

    @Override
    public String toString() {
        return "BaggageAllowance{" +
                "quantity=" + quantity +
                ", baggageType=" + baggageType +
                '}';
    }
}