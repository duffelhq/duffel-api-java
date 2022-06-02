package com.duffel.model.response;

import com.duffel.model.Airline;
import com.duffel.model.Conditions;
import com.duffel.model.Data;
import com.duffel.model.Passenger;
import com.duffel.model.response.offer.Slice;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Getter
@ToString
public class Offer extends Data<Offer> {

    @JsonProperty("allowed_passenger_identity_document_types")
    private List<String> allowedPassengerIdentityDocumentTypes;

    @JsonProperty("conditions")
    private Conditions conditions;

    @JsonProperty("base_amount")
    private Double baseAmount;

    @JsonProperty("base_currency")
    private String baseCurrency;

    @JsonProperty("created_at")
    private LocalDateTime createdAt;

    @JsonProperty("expires_at")
    private LocalDateTime expiresAt;

    @JsonProperty("id")
    private String id;

    @JsonProperty("live_mode")
    private boolean liveMode;

    @JsonProperty("owner")
    private Airline owner;

    @JsonProperty("partial")
    private boolean partial;

    @JsonProperty("passenger_identity_documents_required")
    private boolean passengerIdentityDocumentsRequired;

    @JsonProperty("passengers")
    private List<Passenger> passengers;

    @JsonProperty("payment_requirements")
    private PaymentRequirements paymentRequirements;

    @JsonProperty("slices")
    private List<Slice> slices;

    @JsonProperty("tax_amount")
    private Double taxAmount;

    @JsonProperty("tax_currency")
    private String taxCurrency;

    @JsonProperty("total_amount")
    private Double totalAmount;

    @JsonProperty("total_currency")
    private String totalCurrency;

    @JsonProperty("total_emissions_kg")
    private String totalEmissionsKg;

    @JsonProperty("updated_at")
    private LocalDateTime updatedAt;

}
