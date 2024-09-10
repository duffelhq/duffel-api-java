package com.duffel.model.response;

import com.duffel.model.Data;
import com.duffel.model.PaymentType;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

/**
 * Payment response.
 */
@EqualsAndHashCode(callSuper = true)
@Getter
@ToString
public class PaymentResponse extends Data<PaymentResponse> {

    /**
     * The price of the payment
     * Example: "30.20"
     */
    @JsonProperty("amount")
    private BigDecimal amount;

    /**
     * The ISO 8601 datetime at which the payment was created
     * Example: "2020-04-11T15:48:11.642Z"
     */
    @JsonProperty("created_at")
    private ZonedDateTime createdAt;

    /**
     * The currency of the amount, as an ISO 4217 currency code. It will match your organisation's billing currency
     * unless you’re using Duffel as an accredited IATA agent, in which case it will be in the currency provided by
     * the airline (which will usually be based on the country where your IATA agency is registered).
     * Example: "GBP"
     */
    @JsonProperty("currency")
    private String currency;

    /**
     * Duffel's unique identifier for the payment
     * Example: "pay_00009hthhsUZ8W4LxQgkjo"
     */
    @JsonProperty("id")
    private String id;

    /**
     * Whether the Payment was created in live mode. This field will be set to true if the Payment was created in live
     * mode, or false if it was created in test mode.
     * Example: false
     */
    @JsonProperty("live_mode")
    private boolean liveMode;

    /**
     * The type of payment applied to this order. If you are an IATA agent with your own agreements with airlines, in
     * some cases, you can pay using ARC/BSP cash by specifying arc_bsp_cash. Otherwise, you must pay using your
     * Duffel account's balance by specifying balance. In test mode, your balance is unlimited. If you're not sure
     * which of these options applies to you, get in touch with the Duffel support team at help@duffel.com.
     * Possible values: "arc_bsp_cash" or "balance"
     */
    @JsonProperty("type")
    private PaymentType type;

}
