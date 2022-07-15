package com.duffel.model.response.offer;

import com.duffel.model.response.BaggageType;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@EqualsAndHashCode
@Getter
@ToString
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

}