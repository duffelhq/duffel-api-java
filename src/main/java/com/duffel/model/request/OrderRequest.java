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

@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@ToString
public class OrderRequest extends Data<Order> {

    /// <summary>
    /// The type of order. You can only use hold with offers where payment_requirements.requires_instant_payment is false.
    /// When booking an offer with type hold, do not specify payments or services keys.
    /// Possible values: <see cref="OrderType"/>
    /// </summary>
    @JsonProperty("type")
    private OrderType type;

    /// <summary>
    /// The services you want to book along with the first selected offer. This key should be omitted when the order’s
    /// type is hold, as we do not support services for hold orders yet.
    /// </summary>
    @JsonProperty("services")
    private List<Service> services;

    /// <summary>
    /// The ids of the offers you want to book. You must specify an array containing exactly one selected offer.
    /// Note that you can only book one offer per offer request.
    /// </summary>
    @JsonProperty("selected_offers")
    private List<String> selectedOffers;

    /// <summary>
    /// The personal details of the passengers, expanding on the information initially provided when creating the offer request
    /// </summary>
    @JsonProperty("passengers")
    private List<OrderPassenger> passengers;

    /// <summary>
    /// The payment details to use to pay for the order. This key should be omitted when the order’s type is hold
    /// </summary>
    @JsonProperty("payments")
    private List<Payment> payments;

    /// <summary>
    /// Metadata contains a set of key-value pairs that you can attach to an object. It can be useful for storing
    /// additional information about the object, in a structured format. Duffel does not use this information. You
    /// should not store sensitive information in this field.
    /// The metadata is a collection of key-value pairs, both of which are strings. You can store a maximum of
    /// 50 key-value pairs, where each key has a maximum length of 40 characters and each value has a maximum length
    /// of 500 characters.
    /// Keys must only contain numbers, letters, dashes, or underscores.
    /// Example: {"payment_intent_id":"pit_00009htYpSCXrwaB9DnUm2"}
    /// </summary>
    @JsonProperty("metadata")
    private Map<String, String> metadata;

    @EqualsAndHashCode
    @Getter
    @Setter
    @ToString
    public static class Service {
        /// <summary>
        /// The id of the service from the offer's available_services that you want to book
        /// </summary>
        @JsonProperty("id")
        private String id;

        /// <summary>
        /// The quantity of the service to book. This will always be 1 for seat services.
        /// </summary>
        @JsonProperty("quantity")
        private Integer quantity;
    }
}
