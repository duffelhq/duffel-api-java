package com.duffel.model.request;

import com.duffel.model.Data;
import com.duffel.model.response.OrderCancellation;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Order cancellation request.
 */
@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@ToString
public class OrderCancellationRequest extends Data<OrderCancellation> {

    /**
     * Duffel's unique identifier for the order
     */
    @JsonProperty("order_id")
    private String orderId;

}
