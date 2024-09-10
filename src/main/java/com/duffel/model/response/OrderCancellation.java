package com.duffel.model.response;

import com.duffel.model.RefundDestination;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

/**
 * Order cancellation.
 */
@EqualsAndHashCode
@Getter
@ToString
public class OrderCancellation {

    /**
     * The ISO 8601 datetime that indicates when the order cancellation was confirmed
     */
    @JsonProperty("confirmed_at")
    private ZonedDateTime confirmedAt;

    /**
     * The ISO 8601 datetime at which the order cancellation was created
     */
    @JsonProperty("created_at")
    private ZonedDateTime createdAt;

    /**
     * The ISO 8601 datetime by which this cancellation must be confirmed
     */
    @JsonProperty("expires_at")
    private ZonedDateTime expiresAt;

    /**
     * Duffel's unique identifier for the order cancellation
     */
    @JsonProperty("id")
    private String id;

    /**
     * Whether the order cancellation was created in live mode. This field will be set to true if the order
     * cancellation was created in live mode, or false if it was created in test mode.
     */
    @JsonProperty("live_mode")
    private boolean isLiveMode;

    /**
     * Duffel's unique identifier for the order
     */
    @JsonProperty("order_id")
    private String orderId;

    /**
     * The amount that will be returned to the original payment method if the order is cancelled, determined according
     * to the fare conditions. This may be 0.00 if the fare is non-refundable. It will include the refund amount of
     * the flights and the services booked.
     */
    @JsonProperty("refund_amount")
    private BigDecimal refundAmount;

    /**
     * The currency of the refund_amount, as an ISO 4217 currency code. It will match your organisation's billing
     * currency unless you're using Duffel as an accredited IATA agent, in which case it will be in the currency
     * provided by the airline (which will usually be based on the country where your IATA agency is registered).
     * For hold orders that are awaiting payment, the refund amount will always be 0.00.
     */
    @JsonProperty("refund_currency")
    private String refundCurrency;

    /**
     * Where the refund, once confirmed, will be sent. card is currently a restricted feature. awaiting_payment is
     * for pay later orders where no payment has been made yet.
     */
    @JsonProperty("refund_to")
    private RefundDestination refundTo;

}
