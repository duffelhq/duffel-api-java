package com.duffel.model.response;

import com.duffel.model.Data;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Offer extends Data<Offer> {

    @JsonProperty("allowed_passenger_identity_document_types")
    public List<String> allowedPassengerIdentityDocumentTypes;

    @JsonProperty("base_amount")
    public Double baseAmount;

    @JsonProperty("base_currency")
    public String baseCurrency;

    @JsonProperty("created_at")
    public LocalDateTime createdAt;

    @JsonProperty("updated_at")
    public LocalDateTime updatedAt;

    @JsonProperty("expires_at")
    public LocalDateTime expiresAt;

    @JsonProperty("id")
    public String id;

    @JsonProperty("live_mode")
    public boolean liveMode;

    @Override
    public String toString() {
        return "Offer{" +
                "allowedPassengerIdentityDocumentTypes=" + allowedPassengerIdentityDocumentTypes +
                ", baseAmount=" + baseAmount +
                ", baseCurrency='" + baseCurrency + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", expiresAt=" + expiresAt +
                ", id='" + id + '\'' +
                ", liveMode=" + liveMode +
                '}';
    }
}
