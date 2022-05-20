package com.duffel.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Passenger {

    @JsonProperty("family_name")
    public String familyName;

    @JsonProperty("given_name")
    public String givenName;

    @JsonProperty("type")
    public PassengerType type;

    @JsonProperty("age")
    public Integer age;

    @JsonProperty("loyalty_programme_accounts")
    public List<LoyaltyProgrammeAccount> loyaltyProgrammeAccountList;

    public enum PassengerType {
        adult,
        child,
        infant_without_seat
    }

    @Override
    public String toString() {
        return "Passenger{" +
                "familyName='" + familyName + '\'' +
                ", givenName='" + givenName + '\'' +
                ", type=" + type +
                ", age=" + age +
                ", loyaltyProgrammeAccountList=" + loyaltyProgrammeAccountList +
                '}';
    }
}
