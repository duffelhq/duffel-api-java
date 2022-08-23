package com.duffel.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * Payment requirements for an order.
 */
@EqualsAndHashCode
@Getter
@ToString
public class PaymentRequirements {

    /**
     * The ISO 8601 datetime by which you must pay for this offer. At this time, if still unpaid, the reserved space on
     * the flight(s) will be released, and you will have to create a new order. This will be null when the offer
     * requires immediate payment - that is, when requires_instant_payment is true.
     * Example: "2020-01-17T10:42:14Z"
     */
    @JsonProperty("payment_required_by")
    private LocalDateTime paymentRequiredBy;

    /**
     * The ISO 8601 datetime at which the price associated with the order will no longer be guaranteed by the airline
     * and may change before payment. This will be null when requires_instant_payment is true.
     * Example: "2020-01-17T10:42:14"
     */
    @JsonProperty("price_guarantee_expires_at")
    private LocalDateTime priceGuaranteeExpiresAt;

    /**
     * When payment is required at the time of booking this will be true and payment_required_by and
     * price_guarantee_expires_at will be null. When payment can be made at a time after booking, this will be false
     * and the time limits on the payment will be provided in payment_required_by and price_guarantee_expires_at.
     * Example: false
     */
    @JsonProperty("requires_instant_payment")
    private boolean requiresInstantPayment;

}
