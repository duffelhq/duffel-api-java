package com.duffel.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LoyaltyProgrammeAccount {

    ///  <summary>
    ///  The passenger's account number for this Loyalty Programme Account
    ///  </summary>
    @JsonProperty("account_number")
    public String accountNumber;

    ///  <summary>
    ///  The IATA code for the airline that this Loyalty Programme Account belongs to
    ///  </summary>
    @JsonProperty("airline_iata_code")
    public String airlineIataCode;

    @Override
    public String toString() {
        return "LoyaltyProgrammeAccount{" +
                "accountNumber='" + accountNumber + '\'' +
                ", airlineIataCode='" + airlineIataCode + '\'' +
                '}';
    }
}
