package com.duffel.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Airline loyalty account information for loyalty programmes e.g. Miles and More details
 */
@EqualsAndHashCode
@Getter
@Setter
@ToString
public class LoyaltyProgrammeAccount {

    /**
     * The passenger's account number for this Loyalty Programme Account
     */
    @JsonProperty("account_number")
    private String accountNumber;

    /**
     * The IATA code for the airline that this Loyalty Programme Account belongs to
     */
    @JsonProperty("airline_iata_code")
    private String airlineIataCode;

}
