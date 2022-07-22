package com.duffel.model.request;

import com.duffel.model.Data;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@ToString
public class PaymentRequest extends Data<PaymentRequest> {

    ///  <summary>
    ///  The id of the order you want to pay for.
    ///  Example: "ord_00003x8pVDGcS8y2AWCoWv"
    ///  </summary>
    @JsonProperty("order_id")
    private String orderId;

    ///  <summary>
    ///  The payment details to use to pay for the order
    ///  </summary>
    @JsonProperty("payment")
    private Payment payment;

}
