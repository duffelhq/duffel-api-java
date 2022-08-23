package com.duffel.model.request;

import com.duffel.model.Data;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Payment information for an order.
 */
@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@ToString
public class PaymentRequest extends Data<PaymentRequest> {

    /**
     * The id of the order you want to pay for.
     * Example: "ord_00003x8pVDGcS8y2AWCoWv"
     */
    @JsonProperty("order_id")
    private String orderId;

    /**
     * The payment details to use to pay for the order
     */
    @JsonProperty("payment")
    private Payment payment;

}
