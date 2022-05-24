package com.duffel.model.response;

import com.duffel.model.Airline;
import com.duffel.model.Conditions;
import com.duffel.model.Data;
import com.duffel.model.Passenger;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.util.List;

public class Offer extends Data<Offer> {

    @JsonProperty("allowed_passenger_identity_document_types")
    public List<String> allowedPassengerIdentityDocumentTypes;

    @JsonProperty("conditions")
    public Conditions conditions;

    @JsonProperty("base_amount")
    public Double baseAmount;

    @JsonProperty("base_currency")
    public String baseCurrency;

    @JsonProperty("created_at")
    public LocalDateTime createdAt;

    @JsonProperty("expires_at")
    public LocalDateTime expiresAt;

    @JsonProperty("id")
    public String id;

    @JsonProperty("live_mode")
    public boolean liveMode;

    @JsonProperty("owner")
    public Airline owner;

    @JsonProperty("partial")
    public boolean partial;

    @JsonProperty("passenger_identity_documents_required")
    public boolean passengerIdentityDocumentsRequired;

    @JsonProperty("passengers")
    public List<Passenger> passengers;

    @JsonProperty("payment_requirements")
    public PaymentRequirements paymentRequirements;

    @JsonProperty("slices")
    public List<Slice> slices;

    @JsonProperty("tax_amount")
    public Double taxAmount;

    @JsonProperty("tax_currency")
    public String taxCurrency;

    @JsonProperty("total_amount")
    public Double totalAmount;

    @JsonProperty("total_currency")
    public String totalCurrency;

    @JsonProperty("total_emissions_kg")
    public String totalEmissionsKg;

    @JsonProperty("updated_at")
    public LocalDateTime updatedAt;

    @Override
    public String toString() {
        return "Offer{" +
                "allowedPassengerIdentityDocumentTypes=" + allowedPassengerIdentityDocumentTypes +
                ", conditions=" + conditions +
                ", baseAmount=" + baseAmount +
                ", baseCurrency='" + baseCurrency + '\'' +
                ", createdAt=" + createdAt +
                ", expiresAt=" + expiresAt +
                ", id='" + id + '\'' +
                ", liveMode=" + liveMode +
                ", owner=" + owner +
                ", partial=" + partial +
                ", passengerIdentityDocumentsRequired=" + passengerIdentityDocumentsRequired +
                ", passengers=" + passengers +
                ", paymentRequirements=" + paymentRequirements +
                ", slices=" + slices +
                ", taxAmount=" + taxAmount +
                ", taxCurrency='" + taxCurrency + '\'' +
                ", totalAmount=" + totalAmount +
                ", totalCurrency='" + totalCurrency + '\'' +
                ", totalEmissionsKg=" + totalEmissionsKg +
                ", updatedAt=" + updatedAt +
                '}';
    }

}
