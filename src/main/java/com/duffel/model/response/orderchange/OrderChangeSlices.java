package com.duffel.model.response.orderchange;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

/**
 * Slices to add and remove on an order.
 */
@EqualsAndHashCode
@Getter
@ToString
public class OrderChangeSlices {

    /**
     * The slices that will be added to the order
     */
    @JsonProperty("add")
    private List<OrderChangeSlice> add;

    /**
     * The slices that will be removed from the order
     */
    @JsonProperty("remove")
    private List<OrderChangeSlice> remove;

}
