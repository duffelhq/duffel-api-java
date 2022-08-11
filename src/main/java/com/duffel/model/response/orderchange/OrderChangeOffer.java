package com.duffel.model.response.orderchange;

import com.duffel.model.Data;
import com.duffel.model.RefundDestination;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Getter
@ToString
public class OrderChangeOffer extends Data<OrderChangeOffer> {

    ///  <summary>
    ///  The amount that will be charged or returned to the original payment method if refunded, determined according
    ///  to the fare conditions. This may be negative to reflect a refund.
    ///  </summary>
    @JsonProperty("change_total_amount")
    private BigDecimal changeTotalAmount;

    ///  <summary>
    ///  The currency of the change_total_amount, as an ISO 4217 currency code. It will match your organisation's
    ///  billing currency unless you're using Duffel as an accredited IATA agent, in which case it will be in the
    ///  currency provided by the airline (which will usually be based on the country where your IATA agency is
    ///  registered).
    ///  </summary>
    @JsonProperty("change_total_currency")
    private String changeTotalCurrency;

    ///  <summary>
    ///  The ISO 8601 datetime at which the offer was created
    ///  </summary>
    @JsonProperty("created_at")
    private LocalDateTime createdAt;

    ///  <summary>
    ///  The ISO 8601 datetime at which the offer will expire and no longer be usable to create an order
    ///  </summary>
    @JsonProperty("expires_at")
    private LocalDateTime expiresAt;

    ///  <summary>
    ///  Duffel's unique identifier for the order change offer
    ///  </summary>
    ///  <example>"oco_0000A3vUda8dKRtUSQPSXw"</example>
    @JsonProperty("id")
    private String id;

    ///  <summary>
    ///  Whether the order change offer was created in live mode. This field will be set to true if the order
    ///  change offer was created in live mode, or false if it was created in test mode.
    ///  </summary>
    @JsonProperty("live_mode")
    private boolean liveMode;

    ///  <summary>
    ///  The price of this offer if it was newly purchased
    ///  </summary>
    @JsonProperty("new_total_amount")
    private BigDecimal newTotalAmount;

    ///  <summary>
    ///  The currency of the new_total_amount, as an ISO 4217 currency code. It will match your organisation's billing currency unless youre using Duffel as an accredited IATA agent, in which case it will be in the currency provided by the airline (which will usually be based on the country where your IATA agency is registered).
    ///  </summary>
    @JsonProperty("new_total_currency")
    private String newTotalCurrency;

    ///  <summary>
    ///  The ID for an order change if one has already been created from this order change offer
    ///  </summary>
    ///  <example>"oce_0000A4QasEUIjJ6jHKfhHU"</example>
    @JsonProperty("order_change_id")
    private String orderChangeId;

    ///  <summary>
    ///  The penalty imposed by the airline for making this change
    ///  </summary>
    @JsonProperty("penalty_total_amount")
    private BigDecimal penaltyTotalAmount;

    ///  <summary>
    ///  The currency of the penalty_total_amount, as an ISO 4217 currency code. It will match your organisation's
    ///  billing currency unless you're using Duffel as an accredited IATA agent, in which case it will be in the
    ///  currency provided by the airline (which will usually be based on the country where your IATA agency is
    ///  registered).
    ///  </summary>
    @JsonProperty("penalty_total_currency")
    private String penaltyTotalCurrency;

    ///  <summary>
    ///  Where the refund, once confirmed, will be sent. card is currently a restricted feature. awaiting_payment is
    ///  for pay later orders where no payment has been made yet.
    ///  </summary>
    ///  <value>Possible values: "arc_bsp_cash", "balance", "card", "voucher", or "awaiting_payment"</value>
    @JsonProperty("refund_to")
    private RefundDestination refundTo;

    ///  <summary>
    ///  The ISO 8601 datetime at which the offer was last updated
    ///  </summary>
    @JsonProperty("updated_at")
    private LocalDateTime updatedAt;

    ///  <summary>
    ///  The slices within an order change that are being added to and/or removed from the order
    ///  </summary>
    @JsonProperty("slices")
    private OrderChangeSlices slices;
}