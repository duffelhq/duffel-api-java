package com.duffel.model.request;

import com.duffel.model.PaymentType;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

/**
 * Payment details.
 */
@EqualsAndHashCode
@Getter
@Setter
@ToString
public class Payment {
    /**
    * The amount of the payment. This should be the same as the total_amount of the offer specified in
    * selected_offers, plus the total_amount of all the services specified in services.
    */
    @JsonProperty("amount")
    private BigDecimal amount;

    /**
    * The currency of the amount, as an ISO 4217 currency code. This should be the same as the total_currency of
    * the offer specified in selected_offers.
    */
    @JsonProperty("currency")
    private String currency;

    /**
    * The type of payment you want to apply to the order. If you are an IATA agent with your own agreements with
    * airlines, in some cases, you can pay using ARC/BSP cash by specifying arc_bsp_cash. Otherwise, you must pay
    * using your Duffel account's balance by specifying balance. In test mode, your balance is unlimited. If
    * you're not sure which of these options applies to you, get in touch with the Duffel support team
    * at help@duffel.com.
    */
    @JsonProperty("type")
    private PaymentType type;
}
