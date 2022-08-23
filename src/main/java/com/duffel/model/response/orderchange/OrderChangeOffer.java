package com.duffel.model.response.orderchange;

import com.duffel.model.Data;
import com.duffel.model.RefundDestination;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Change order possible offer.
 */
@EqualsAndHashCode(callSuper = true)
@Getter
@ToString
public class OrderChangeOffer extends Data<OrderChangeOffer> {

    /**
     * The amount that will be charged or returned to the original payment method if refunded, determined according
     * to the fare conditions. This may be negative to reflect a refund.
     */
    @JsonProperty("change_total_amount")
    private BigDecimal changeTotalAmount;

    /**
     * The currency of the change_total_amount, as an ISO 4217 currency code. It will match your organisation's
     * billing currency unless you're using Duffel as an accredited IATA agent, in which case it will be in the
     * currency provided by the airline (which will usually be based on the country where your IATA agency is
     * registered).
     */
    @JsonProperty("change_total_currency")
    private String changeTotalCurrency;

    /**
     * The ISO 8601 datetime at which the offer was created
     */
    @JsonProperty("created_at")
    private LocalDateTime createdAt;

    /**
     * The ISO 8601 datetime at which the offer will expire and no longer be usable to create an order
     */
    @JsonProperty("expires_at")
    private LocalDateTime expiresAt;

    /**
     * Duffel's unique identifier for the order change offer
     * e.g. "oco_0000A3vUda8dKRtUSQPSXw"
     */
    @JsonProperty("id")
    private String id;

    /**
     * Whether the order change offer was created in live mode. This field will be set to true if the order
     * change offer was created in live mode, or false if it was created in test mode.
     */
    @JsonProperty("live_mode")
    private boolean liveMode;

    /**
     * The price of this offer if it was newly purchased
     */
    @JsonProperty("new_total_amount")
    private BigDecimal newTotalAmount;

    /**
     * The currency of the new_total_amount, as an ISO 4217 currency code. It will match your organisation's billing currency unless youre using Duffel as an accredited IATA agent, in which case it will be in the currency provided by the airline (which will usually be based on the country where your IATA agency is registered).
     */
    @JsonProperty("new_total_currency")
    private String newTotalCurrency;

    /**
     * The ID for an order change if one has already been created from this order change offer
     * e.g. "oce_0000A4QasEUIjJ6jHKfhHU"
     */

    @JsonProperty("order_change_id")
    private String orderChangeId;

    /**
     * The penalty imposed by the airline for making this change
     */
    @JsonProperty("penalty_total_amount")
    private BigDecimal penaltyTotalAmount;

    /**
     * The currency of the penalty_total_amount, as an ISO 4217 currency code. It will match your organisation's
     * billing currency unless you're using Duffel as an accredited IATA agent, in which case it will be in the
     * currency provided by the airline (which will usually be based on the country where your IATA agency is
     * registered).
     */
    @JsonProperty("penalty_total_currency")
    private String penaltyTotalCurrency;

    /**
     * Where the refund, once confirmed, will be sent. card is currently a restricted feature. awaiting_payment is
     * for pay later orders where no payment has been made yet.
     * Possible values: "arc_bsp_cash", "balance", "card", "voucher", or "awaiting_payment"
     */
    @JsonProperty("refund_to")
    private RefundDestination refundTo;

    /**
     * The ISO 8601 datetime at which the offer was last updated
     */
    @JsonProperty("updated_at")
    private LocalDateTime updatedAt;

    /**
     * The slices within an order change that are being added to and/or removed from the order
     */
    @JsonProperty("slices")
    private OrderChangeSlices slices;
}