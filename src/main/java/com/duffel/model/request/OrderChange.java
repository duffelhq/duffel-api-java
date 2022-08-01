package com.duffel.model.request;

import com.duffel.model.OrderChangeSlices;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@EqualsAndHashCode
@Getter
@Setter
@ToString
public class OrderChange {

    ///  <summary>
    ///  The slices that make up this offer request. One-way journeys can be expressed using one slice,
    ///  whereas return trips will need two.
    ///  </summary>
    @JsonProperty("slices")
    private OrderChangeSlices slices;

    ///  <summary>
    ///  The order ID you wish to change
    ///  Example: "ord_0000A3bQ8FJIQoEfuC07n6"
    ///  </summary>
    @JsonProperty("order_id")
    private String orderId;

}
