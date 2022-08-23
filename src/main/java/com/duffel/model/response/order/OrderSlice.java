package com.duffel.model.response.order;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * Order slice information.
 */
@EqualsAndHashCode(callSuper = true)
@Getter
@ToString
public class OrderSlice extends com.duffel.model.response.Slice {

    /**
     * Whether this slice can be changed. This can only be true for paid orders.
     */
    @JsonProperty("changeable")
    private Boolean changeable;

}
