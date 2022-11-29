package com.duffel.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * Order change request.
 */
@EqualsAndHashCode
@Getter
@Setter
@ToString
public class OrderChangeServices {

    /**
     * The ID for the order we want to update.
     */
    @JsonProperty("order_id")
    private String orderId;

    /**
     The payment details to pay for the services.
     */
    @JsonProperty("payment")
    private Payment payment;

    /**
     * The services you want to add onto this order.
     */
    @JsonProperty("add_services")
    private List<ServiceRequest> addServices;

}
