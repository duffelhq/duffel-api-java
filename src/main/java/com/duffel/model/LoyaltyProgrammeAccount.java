package com.duffel.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LoyaltyProgrammeAccount {

    @JsonProperty("account_number")
    public String accountNumber;

    @JsonProperty("airline_iata_code")
    public String airlineIataCode;

}
