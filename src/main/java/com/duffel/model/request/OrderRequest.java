package com.duffel.model.request;

import com.duffel.model.Data;
import com.duffel.model.OrderPassenger;
import com.duffel.model.OrderType;
import com.duffel.model.response.Order;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.Map;

/**
 * Order placement request.
 */
@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@ToString
public class OrderRequest extends Data<Order> {

    /**
     * The type of order. You can only use hold with offers where payment_requirements.requires_instant_payment is false.
     * When booking an offer with type hold, do not specify payments or services keys.
     * Possible values: OrderType
     */
    @JsonProperty("type")
    private OrderType type;

    /**
     * The services you want to book along with the first selected offer. This key should be omitted when the order’s
     * type is hold, as we do not support services for hold orders yet.
     */
    @JsonProperty("services")
    private List<Service> services;

    /**
     * The ids of the offers you want to book. You must specify an array containing exactly one selected offer.
     * Note that you can only book one offer per offer request.
     */
    @JsonProperty("selected_offers")
    private List<String> selectedOffers;

    /**
     * The personal details of the passengers, expanding on the information initially provided when creating the offer request
     */
    @JsonProperty("passengers")
    private List<OrderPassenger> passengers;

    /**
     * The payment details to use to pay for the order. This key should be omitted when the order’s type is hold
     */
    @JsonProperty("payments")
    private List<Payment> payments;

    /**
     * Metadata contains a set of key-value pairs that you can attach to an object. It can be useful for storing
     * additional information about the object, in a structured format. Duffel does not use this information. You
     * should not store sensitive information in this field.
     * The metadata is a collection of key-value pairs, both of which are strings. You can store a maximum of
     * 50 key-value pairs, where each key has a maximum length of 40 characters and each value has a maximum length
     * of 500 characters.
     * Keys must only contain numbers, letters, dashes, or underscores.
     * Example: {"payment_intent_id":"pit_00009htYpSCXrwaB9DnUm2"}
     */
    @JsonProperty("metadata")
    private Map<String, String> metadata;

}
