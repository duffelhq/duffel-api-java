package com.duffel.model.response.seatmap;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;

/**
 * Service that is available for a seat.
 */
@EqualsAndHashCode
@Getter
@ToString
public class SeatService {

    /**
     * Duffel's unique identifier for the service
     * e.g. ase_00009UhD4ongolulWd9123
     */
    @JsonProperty("id")
    private String id;

    /**
     * The passenger that this seat is for
     */
    @JsonProperty("passenger_id")
    private String passengerId;

    /**
     * The total price of the seat, including taxes
     */
    @JsonProperty("total_amount")
    private BigDecimal totalAmount;

    /**
     * The currency of the total_amount, as an ISO 4217 currency code. It will match your organisation's billing
     * currency unless you're using Duffel as an accredited IATA agent, in which case it will be in the currency
     * provided by the airline (which will usually be based on the country where your IATA agency is registered).
     */
    @JsonProperty("total_currency")
    private String totalCurrency;

}
