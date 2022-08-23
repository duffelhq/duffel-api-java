package com.duffel.model.request;

import com.duffel.model.Data;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Order change payment request.
 */
@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@ToString
public class OrderChangePaymentRequest extends Data<OrderChangePaymentRequest> {

    /**
     * The payment details to use to pay for the order
     */
    @JsonProperty("payment")
    private Payment payment;

}
