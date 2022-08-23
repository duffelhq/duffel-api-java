package com.duffel.model.response;

import com.duffel.model.Airline;
import com.duffel.model.Conditions;
import com.duffel.model.Data;
import com.duffel.model.Passenger;
import com.duffel.model.response.offer.OfferSlice;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Flight offer.
 */
@EqualsAndHashCode(callSuper = true)
@Getter
@ToString
public class Offer extends Data<Offer> {

    /**
     * The types of identity documents that may be provided for the passengers when creating an order based on this
     * offer. Currently, the only supported type is passport. If this is [], then you must not provide identity
     * documents.
     * Example: ["passport"]
     */
    @JsonProperty("allowed_passenger_identity_document_types")
    private List<String> allowedPassengerIdentityDocumentTypes;

    /**
     * The services that can be booked along with the offer but are not included by default, for example an additional
     * checked bag. This field is only returned in the Get single offer endpoint. When there are no services available,
     * or we don't support services for the airline, this list will be empty. If you want to know which airlines we
     * support services for, please get in touch with the Duffel support team at help@duffel.com.
     */
    @JsonProperty("available_services")
    private List<Service> availableServices;

    /**
     * The conditions associated with this offer, describing the kinds of modifications you can make post-booking and
     * any penalties that will apply to those modifications. This information assumes the condition is applied to
     * all the slices and passengers associated with this offer - for information at the slice level
     * (e.g. "what happens if I just want to change the first slice?") refer to the slices. If a particular kind of
     * modification is allowed, you may not always be able to take action through the Duffel API. In some cases, you
     * may need to contact the Duffel support team or the airline directly.
     */
    @JsonProperty("conditions")
    private Conditions conditions;

    /**
     * The base price of the offer for all passengers, excluding taxes. It does not include the base amount of any
     * service(s) that might be booked with the offer.
     * Example: "30.20"
     */
    @JsonProperty("base_amount")
    private BigDecimal baseAmount;

    /**
     * The currency of the base_amount, as an ISO 4217 currency code. It will match your organisation's billing currency
     * unless you’re using Duffel as an accredited IATA agent, in which case it will be in the currency provided by the
     * airline (which will usually be based on the country where your IATA agency is registered).
     * Example: "GBP"
     */
    @JsonProperty("base_currency")
    private String baseCurrency;

    /**
     * The ISO 8601 datetime at which the offer was created
     * Example: "2020-01-17T10:12:14.545Z"
     */
    @JsonProperty("created_at")
    private LocalDateTime createdAt;

    /**
     * The ISO 8601 datetime expiry of the offer before which the traveller should use this offer to create an order.
     * After this time the offer can no longer be used to create an order.
     * Example: "2020-01-17T10:42:14.545Z"
     */
    @JsonProperty("expires_at")
    private LocalDateTime expiresAt;

    /**
     * Duffel's unique identifier for the offer
     * Example: "off_00009htYpSCXrwaB9DnUm0"
     */
    @JsonProperty("id")
    private String id;

    /**
     * Whether the offer request was created in live mode. This field will be set to true if the offer request was
     * created in live mode, or false if it was created in test mode.
     * Example: true
     */
    @JsonProperty("live_mode")
    private boolean liveMode;

    /**
     * The airline which provided the offer
     */
    @JsonProperty("owner")
    private Airline owner;

    /**
     * Partial offers are a new concept we're introducing as a part of a new multistep search flow that we're
     * currently experimenting with. A partial offer can't be booked directly, but it can be combined with other partial
     * offers to form a full offer. Partial offers are only ever returned through the multistep search flow. So there's
     * no need to add any handling to deal with partial offers if you're using the traditional OfferRequest search flow
     * to create offers.
     * Example: true
     */
    @JsonProperty("partial")
    private boolean partial;

    /**
     * Whether identity documents must be provided for each of the passengers when creating an order based on this
     * offer. If this is true, you must provide an identity document for every passenger.
     * Example: false
     */
    @JsonProperty("passenger_identity_documents_required")
    private boolean passengerIdentityDocumentsRequired;

    /**
     * The passengers included in the offer
     */
    @JsonProperty("passengers")
    private List<Passenger> passengers;

    /**
     * The payment requirements for this offer
     */
    @JsonProperty("payment_requirements")
    private PaymentRequirements paymentRequirements;

    /**
     * The slices that make up this offer. Each slice will include one or more segments, the specific flights that the
     * airline is offering to take the passengers from the slice's origin to its destination. Partial offers contain a
     * single slice as each partial offer is for a particular slice of the journey.
     */
    @JsonProperty("slices")
    private List<OfferSlice> slices;

    /**
     * The amount of tax payable on the offer for all passengers
     * Example: "40.80"
     */
    @JsonProperty("tax_amount")
    private BigDecimal taxAmount;

    /**
     * The currency of the tax_amount, as an ISO 4217 currency code. It will match your organisation's billing currency
     * unless you’re using Duffel as an accredited IATA agent, in which case it will be in the currency provided by the
     * airline (which will usually be based on the country where your IATA agency is registered).
     * Example: "GBP"
     */
    @JsonProperty("tax_currency")
    private String taxCurrency;

    /**
     * The total price of the offer for all passengers, including taxes. It does not include the total price of any
     * service(s) that might be booked with the offer.
     * Example: "45.00"
     */
    @JsonProperty("total_amount")
    private BigDecimal totalAmount;

    /**
     * The currency of the total_amount, as an ISO 4217 currency code. It will match your organisation's billing
     * currency unless you’re using Duffel as an accredited IATA agent, in which case it will be in the currency
     * provided by the airline (which will usually be based on the country where your IATA agency is registered).
     * Example: "GBP"
     */
    @JsonProperty("total_currency")
    private String totalCurrency;

    /**
     * An estimate of the total carbon dioxide (CO₂) emissions when all of the passengers fly this offer's itinerary,
     * measured in kilograms
     * Example: "460"
     */
    @JsonProperty("total_emissions_kg")
    private String totalEmissionsKg;

    /**
     * The ISO 8601 datetime at which the offer was last updated
     * Example: "2020-01-17T10:12:14.545Z"
     */
    @JsonProperty("updated_at")
    private LocalDateTime updatedAt;

}
