package com.duffel.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public class PaymentRequirements {
    @JsonProperty("payment_required_by")
    public LocalDateTime paymentRequiredBy;

    @JsonProperty("price_guarantee_expires_at")
    public LocalDateTime priceGuaranteeExpiresAt;

    @JsonProperty("requires_instant_payment")
    public boolean requiresInstantPayment;

    @Override
    public String toString() {
        return "PaymentRequirements{" +
                "paymentRequiredBy=" + paymentRequiredBy +
                ", priceGuaranteeExpiresAt=" + priceGuaranteeExpiresAt +
                ", requiresInstantPayment=" + requiresInstantPayment +
                '}';
    }
}
