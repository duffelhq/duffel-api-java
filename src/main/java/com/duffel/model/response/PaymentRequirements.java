package com.duffel.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@EqualsAndHashCode
@Getter
@ToString
public class PaymentRequirements {
    @JsonProperty("payment_required_by")
    private LocalDateTime paymentRequiredBy;

    @JsonProperty("price_guarantee_expires_at")
    private LocalDateTime priceGuaranteeExpiresAt;

    @JsonProperty("requires_instant_payment")
    private boolean requiresInstantPayment;

}
