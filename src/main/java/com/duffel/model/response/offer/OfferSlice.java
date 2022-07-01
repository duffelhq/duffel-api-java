package com.duffel.model.response.offer;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@Getter
@ToString
public class OfferSlice extends com.duffel.model.response.Slice {

    ///  <summary>
    ///  The name of the fare brand associated with this slice. A fare brand specifies the travel conditions you get on
    ///  your slice made available by the airline. e.g. a British Airways Economy Basic fare will only include a hand
    ///  baggage allowance. It is worth noting that the fare brand names are defined by the airlines themselves and
    ///  therefore they are subject to change without any prior notice. We’re in the process of adding support for
    ///  fare_brand_name across all our airlines, so for now, this field may be null in some offers. This will become
    ///  a non-nullable attribute in the near future.
    ///  Example: "Basic"
    ///  </summary>
    @JsonProperty("fare_brand_name")
    private String fareBrandName;

}
