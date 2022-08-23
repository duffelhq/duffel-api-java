package com.duffel.model.response.offer;

import com.duffel.model.response.BaggageType;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * Baggage allowance.
 */
@EqualsAndHashCode
@Getter
@ToString
public class BaggageAllowance {

    /**
     * The number of this type of bag allowed on the segment.
     * Note that this can currently be 0 in some cases.
     */
    @JsonProperty("quantity")
    public Integer quantity;

    /**
     * The type of the baggage allowance. Possible values defined in BaggageType
     */
    @JsonProperty("type")
    public BaggageType baggageType;

}