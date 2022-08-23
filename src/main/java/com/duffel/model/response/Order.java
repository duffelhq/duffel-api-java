package com.duffel.model.response;

import com.duffel.model.*;
import com.duffel.model.response.order.Passenger;
import com.duffel.model.response.order.PaymentStatus;
import com.duffel.model.response.order.OrderSlice;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * Order information.
 */
@EqualsAndHashCode(callSuper = true)
@Getter
@ToString
public class Order extends Data<Order> {

    /**
     * The airline-initiated changes for this Order
     */
    @JsonProperty("airline_initiated_changes")
    private List<AirlineInitiatedChange> airlineInitiatedChanges;

    /**
     * The base price of the order for all flights and services booked, excluding taxes
     * e.g. "30.20"
     */
    @JsonProperty("base_amount")
    private BigDecimal baseAmount;

    /**
     * The currency of the base_amount, as an ISO 4217 currency code. It will match your organisation's billing
     * currency unless you're using Duffel as an accredited IATA agent, in which case it will be in the currency
     * provided by the airline (which will usually be based on the country where your IATA agency is registered).
     */
    @JsonProperty("base_currency")
    private String baseCurrency;

    /**
     * The airline's reference for the order, sometimes known as a "passenger name record" (PNR) or "record locator".
     * Your customers can use this to check in and manage their booking on the airline's website. Usually, this is
     * made up of six alphanumeric characters, but airlines can have their own formats (for example, easyJet's booking
     * references are 7 alphanumeric characters long and LATAM's references are made up of 13 alphanumeric characters
     * beginning with LA.)
     * e.g. "RZPNX8"
     */

    @JsonProperty("booking_reference")
    private String bookingReference;

    /**
     * The ISO 8601 datetime at which the order was cancelled, if it has been cancelled
     * e.g. "2020-04-11T15:48:11.642Z"
     */
    @JsonProperty("cancelled_at")
    private LocalDateTime cancelledAt;

    /**
     * The conditions associated with this order, describing the kinds of modifications you can make to it and any
     * penalties that will apply to those modifications. This information assumes the condition is applied to all
     * the slices and passengers associated with this order - for information at the slice level (e.g. "what happens
     * if I just want to change the first slice?") refer to the slices. If a particular kind of modification is
     * allowed, you may not always be able to take action through the Duffel API. In some cases, you may need to
     * contact the Duffel support team or the airline directly.
     */
    @JsonProperty("conditions")
    private Conditions conditions;

    /**
     * The ISO 8601 datetime at which the order was created
     * e.g. "2020-04-11T15:48:11.642Z"
     */
    @JsonProperty("created_at")
    private LocalDateTime createdAt;

    /**
     * The documents issued for this order
     */
    @JsonProperty("documents")
    private List<Document> documents;

    /**
     * Duffel's unique identifier for the order
     * e.g. "ord_00009hthhsUZ8W4LxQgkjo"
     */
    @JsonProperty("id")
    private String id;

    /**
     * Whether the order was created in live mode. This field will be set to true if the order was created in live
     * mode, or false if it was created in test mode.
     */
    @JsonProperty("live_mode")
    private boolean liveMode;

    /**
     * Metadata contains a set of key-value pairs that you can attach to an object. It can be useful for storing
     * additional information about the object, in a structured format. Duffel does not use this information. You
     * should not store sensitive information in this field.
     * e.g. {"customer_prefs":"window seat","payment_intent_id":"pit_00009htYpSCXrwaB9DnUm2"}
     */
    @JsonProperty("metadata")
    private Map<String, String> metadata;

    /**
     * The airline who owns the order
     */
    @JsonProperty("owner")
    private Airline owner;

    /**
     * The passengers who are travelling
     */
    @JsonProperty("passengers")
    private List<Passenger> passengers;

    /**
     * The payment status for this order
     */
    @JsonProperty("payment_status")
    private PaymentStatus paymentStatus;

    /**
     * The services booked along with this order
     */
    @JsonProperty("services")
    private List<Service> services;

    /**
     * The slices that make up the itinerary of this order. One-way journeys can be expressed using one slice,
     * whereas return trips will need two.
     */
    @JsonProperty("slices")
    private List<OrderSlice> slices;

    /**
     * Airlines are always the source of truth for orders. The orders returned by the Duffel API are a view of those
     * orders. This field is the ISO 8601 datetime at which the Order was last synced with the airline. If this
     * datetime is in the last minute you can consider the order up to date.
     */
    @JsonProperty("synced_at")
    private LocalDateTime syncedAt;

    /**
     * The amount of tax payable on the order for all the flights booked
     */
    @JsonProperty("tax_amount")
    private BigDecimal taxAmount;

    /**
     * The currency of the tax_amount, as an ISO 4217 currency code. It will match your organisation's billing
     * currency unless you're using Duffel as an accredited IATA agent, in which case it will be in the currency
     * provided by the airline (which will usually be based on the country where your IATA agency is registered).
     */
    @JsonProperty("tax_currency")
    private String taxCurrency;

    /**
     * The total price of the order for all the flights and services booked, including taxes
     */
    @JsonProperty("total_amount")
    private BigDecimal totalAmount;

    /**
     * The currency of the total_amount, as an ISO 4217 currency code. It will match your organisation's billing
     * currency unless you're using Duffel as an accredited IATA agent, in which case it will be in the currency
     * provided by the airline (which will usually be based on the country where your IATA agency is registered).
     */
    @JsonProperty("total_currency")
    private String totalCurrency;

    @EqualsAndHashCode
    @Getter
    @ToString
    public static class Document {
        /**
         * The type of document
         */
        @JsonProperty("type")
        private DocumentType type;

        /**
         * The identifier for the document, in the case of electronic tickets this string represents the payment or
         * the entitlement to fly.
         */
        @JsonProperty("unique_identifier")
        private String uniqueIdentifier;

        /**
         * The list of passenger ids the document applies to
         */
        @JsonProperty("passenger_ids")
        private List<String> passengerIds;
    }

}
