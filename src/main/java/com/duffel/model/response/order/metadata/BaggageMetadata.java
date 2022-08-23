package com.duffel.model.response.order.metadata;

import com.duffel.model.response.BaggageType;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * Baggage specifications.
 */
@EqualsAndHashCode
@Getter
@ToString
public class BaggageMetadata implements Metadata {

    /**
     * The maximum depth that the baggage can have in centimetres
     */
    @JsonProperty("maximum_depth_cm")
    private Integer maximumDepthCm;

    /**
     * The maximum height that the baggage can have in centimetres
     */
    @JsonProperty("maximum_height_cm")
    private Integer maximumHeightCm;

    /**
     * The maximum length that the baggage can have in centimetres
     */
    @JsonProperty("maximum_length_cm")
    private Integer maximumLengthCm;

    /**
     * The maximum weight that the baggage can have in kilogram
     */
    @JsonProperty("maximum_weight_kg")
    private Integer maximumWeightKg;

    /**
     * The type of the baggage
     * Possible values: "checked" or "carry_on"
     */
    @JsonProperty("type")
    private BaggageType baggageType;

}
