package com.duffel.model.response.order;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.time.ZonedDateTime;

/**
 * Status of an order payment.
 */
@EqualsAndHashCode
@Getter
@ToString
public class PaymentStatus {

    /**
    *  Whether a full payment has been made, or the airline is waiting for a payment to be made. This will be set to
    * false if the order has been cancelled or if payment_required_by has elapsed.
    */
    @JsonProperty("awaiting_payment")
    private boolean awaitingPayment;

    /**
    *  The ISO 8601 datetime by which you must pay for this order. At this time, if still unpaid, the reserved space
    * on the flight(s) will be released and you will have to create a new order. This will be null only for orders
    * where awaiting_payment is false.
    */
    @JsonProperty("payment_required_by")
    private ZonedDateTime paymentRequiredBy;

    /**
    *  The ISO 8601 datetime at which the price associated with the order will no longer be guaranteed by the airline
    *  and the order will need to be repriced before payment. This will be null when there is no price guarantee.
    */
    @JsonProperty("price_guarantee_expires_at")
    private ZonedDateTime priceGuaranteeExpiresAt;

}
