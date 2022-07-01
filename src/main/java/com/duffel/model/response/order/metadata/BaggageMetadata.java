package com.duffel.model.response.order.metadata;

import com.duffel.model.response.offer.BaggageType;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@EqualsAndHashCode
@Getter
@ToString
public class BaggageMetadata implements Metadata {

    ///  <summary>
    ///  The maximum depth that the baggage can have in centimetres
    ///  </summary>
    @JsonProperty("maximum_depth_cm")
    private Integer maximumDepthCm;

    ///  <summary>
    ///  The maximum height that the baggage can have in centimetres
    ///  </summary>
    @JsonProperty("maximum_height_cm")
    private Integer maximumHeightCm;

    ///  <summary>
    ///  The maximum length that the baggage can have in centimetres
    ///  </summary>
    @JsonProperty("maximum_length_cm")
    private Integer maximumLengthCm;

    ///  <summary>
    ///  The maximum weight that the baggage can have in kilogram
    ///  </summary>
    @JsonProperty("maximum_weight_kg")
    private Integer maximumWeightKg;

    ///  <summary>
    ///  The type of the baggage
    ///  Possible values: "checked" or "carry_on"
    ///  </summary>
    @JsonProperty("type")
    private BaggageType baggageType;

}
