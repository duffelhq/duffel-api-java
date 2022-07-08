package com.duffel.model.response;

import com.duffel.model.*;
import com.duffel.model.response.order.Passenger;
import com.duffel.model.response.order.PaymentStatus;
import com.duffel.model.response.order.OrderSlice;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@EqualsAndHashCode(callSuper = true)
@Getter
@ToString
public class Order extends Data<Order> {

    ///  <summary>
    ///  The airline-initiated changes for this Order
    ///  </summary>
    @JsonProperty("airline_initiated_changes")
    private List<AirlineInitiatedChange> airlineInitiatedChanges;

    ///  <summary>
    ///  The base price of the order for all flights and services booked, excluding taxes
    ///  </summary>
    ///  <example>"30.20"</example>
    @JsonProperty("base_amount")
    private Double baseAmount;

    ///  <summary>
    ///  The currency of the base_amount, as an ISO 4217 currency code. It will match your organisation's billing
    /// currency unless you're using Duffel as an accredited IATA agent, in which case it will be in the currency
    /// provided by the airline (which will usually be based on the country where your IATA agency is registered).
    ///  </summary>
    @JsonProperty("base_currency")
    private String baseCurrency;

    ///  <summary>
    ///  The airline's reference for the order, sometimes known as a "passenger name record" (PNR) or "record locator".
    /// Your customers can use this to check in and manage their booking on the airline's website. Usually, this is
    /// made up of six alphanumeric characters, but airlines can have their own formats (for example, easyJet's booking
    /// references are 7 alphanumeric characters long and LATAM's references are made up of 13 alphanumeric characters
    /// beginning with LA.)
    ///  </summary>
    ///  <example>"RZPNX8"</example>
    @JsonProperty("booking_reference")
    private String bookingReference;

    ///  <summary>
    ///  The ISO 8601 datetime at which the order was cancelled, if it has been cancelled
    ///  </summary>
    ///  <example>"2020-04-11T15:48:11.642Z"</example>
    @JsonProperty("cancelled_at")
    private LocalDateTime cancelledAt;

    ///  <summary>
    ///  The conditions associated with this order, describing the kinds of modifications you can make to it and any
    ///  penalties that will apply to those modifications. This information assumes the condition is applied to all of
    ///  the slices and passengers associated with this order - for information at the slice level (e.g. "what happens
    ///  if I just want to change the first slice?") refer to the slices. If a particular kind of modification is
    ///  allowed, you may not always be able to take action through the Duffel API. In some cases, you may need to
    ///  contact the Duffel support team or the airline directly.
    ///  </summary>
    @JsonProperty("conditions")
    private Conditions conditions;

    ///  <summary>
    ///  The ISO 8601 datetime at which the order was created
    ///  </summary>
    ///  <example>"2020-04-11T15:48:11.642Z"</example>
    @JsonProperty("created_at")
    private LocalDateTime createdAt;

    ///  <summary>
    ///  The documents issued for this order
    ///  </summary>
    @JsonProperty("documents")
    private List<Document> documents;

    ///  <summary>
    ///  Duffel's unique identifier for the order
    ///  </summary>
    ///  <example>"ord_00009hthhsUZ8W4LxQgkjo"</example>
    @JsonProperty("id")
    private String id;

    ///  <summary>
    ///  Whether the order was created in live mode. This field will be set to true if the order was created in live
    ///  mode, or false if it was created in test mode.
    ///  </summary>
    @JsonProperty("live_mode")
    private boolean liveMode;

    ///  <summary>
    ///  Metadata contains a set of key-value pairs that you can attach to an object. It can be useful for storing
    ///  additional information about the object, in a structured format. Duffel does not use this information. You
    ///  should not store sensitive information in this field.
    ///  </summary>
    ///  <example>{"customer_prefs":"window seat","payment_intent_id":"pit_00009htYpSCXrwaB9DnUm2"}</example>
    @JsonProperty("metadata")
    private Map<String, String> metadata;

    ///  <summary>
    ///  The airline who owns the order
    ///  </summary>
    @JsonProperty("owner")
    private Airline owner;

    ///  <summary>
    ///  The passengers who are travelling
    ///  </summary>
    @JsonProperty("passengers")
    private List<Passenger> passengers;

    ///  <summary>
    ///  The payment status for this order
    ///  </summary>
    @JsonProperty("payment_status")
    private PaymentStatus paymentStatus;

    ///  <summary>
    ///  The services booked along with this order
    ///  </summary>
    @JsonProperty("services")
    private List<Service> services;

    ///  <summary>
    ///  The slices that make up the itinerary of this order. One-way journeys can be expressed using one slice,
    ///  whereas return trips will need two.
    ///  </summary>
    @JsonProperty("slices")
    private List<OrderSlice> slices;

    ///  <summary>
    ///  Airlines are always the source of truth for orders. The orders returned in the Duffel API are a view of those
    /// orders. This field is the ISO 8601 datetime at which the Order was last synced with the airline. If this
    /// datetime is in the last minute you can consider the order up to date.
    ///  </summary>
    @JsonProperty("synced_at")
    private LocalDateTime syncedAt;

    ///  <summary>
    ///  The amount of tax payable on the order for all the flights booked
    ///  </summary>
    @JsonProperty("tax_amount")
    private Double taxAmount;

    ///  <summary>
    ///  The currency of the tax_amount, as an ISO 4217 currency code. It will match your organisation's billing
    /// currency unless you're using Duffel as an accredited IATA agent, in which case it will be in the currency
    /// provided by the airline (which will usually be based on the country where your IATA agency is registered).
    ///  </summary>
    @JsonProperty("tax_currency")
    private String taxCurrency;

    ///  <summary>
    ///  The total price of the order for all the flights and services booked, including taxes
    ///  </summary>
    @JsonProperty("total_amount")
    private Double totalAmount;

    ///  <summary>
    ///  The currency of the total_amount, as an ISO 4217 currency code. It will match your organisation's billing
    ///currency unless you're using Duffel as an accredited IATA agent, in which case it will be in the currency
    // provided by the airline (which will usually be based on the country where your IATA agency is registered).
    ///  </summary>
    @JsonProperty("total_currency")
    private String totalCurrency;

    @EqualsAndHashCode
    @Getter
    @ToString
    public static class Document {
        /// <summary>
        /// The type of document
        /// </summary>
        @JsonProperty("type")
        private DocumentType type;

        /// <summary>
        /// The identifier for the document, in the case of electronic tickets this string represents the payment or
        /// the entitlement to fly.
        /// </summary>
        @JsonProperty("unique_identifier")
        private String uniqueIdentifier;

        ///  <summary>
        ///  The list of passenger ids the document applies to
        ///  </summary>
        @JsonProperty("passenger_ids")
        private List<String> passengerIds;
    }

}
