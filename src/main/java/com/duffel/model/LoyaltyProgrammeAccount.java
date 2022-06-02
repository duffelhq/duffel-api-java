package com.duffel.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@EqualsAndHashCode
@Getter
@ToString
public class LoyaltyProgrammeAccount {

    ///  <summary>
    ///  The passenger's account number for this Loyalty Programme Account
    ///  </summary>
    @JsonProperty("account_number")
    private String accountNumber;

    ///  <summary>
    ///  The IATA code for the airline that this Loyalty Programme Account belongs to
    ///  </summary>
    @JsonProperty("airline_iata_code")
    private String airlineIataCode;

}
