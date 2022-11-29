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

    @JsonProperty("payment")
    private Payment payment;

    @JsonProperty("add_services")
    private List<Service> addServices;

}
