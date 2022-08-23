package com.duffel.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * Passenger details.
 */
@EqualsAndHashCode
@Getter
@Setter
@ToString
public class Passenger {

    /**
     * The passenger's family name. Only space, -, ', and letters from the ASCII, Latin-1 Supplement and
     * Latin Extended-A (with the exceptions of Æ, æ, Ĳ, ĳ, Œ, œ, Þ, and ð) Unicode charts are accepted. All other
     * characters will result in a validation error. The minimum length is 1 character, and the maximum is 20
     * characters.
     * <p>
     * This is only required if you're also including Loyalty Programme Accounts.
     */
    @JsonProperty("family_name")
    private String familyName;

    /**
     * The passenger's given name. Only space, -, ', and letters from the ASCII, Latin-1 Supplement and
     * Latin Extended-A (with the exceptions of Æ, æ, Ĳ, ĳ, Œ, œ, Þ, and ð) Unicode charts are accepted. All other
     * characters will result in a validation error. The minimum length is 1 character, and the maximum is 20
     * characters.
     * <p>
     * This is only required if you're also including Loyalty Programme Accounts.
     */
    @JsonProperty("given_name")
    private String givenName;

    /**
     * The type of the passenger. If the passenger is aged 18 or over, you should specify a type of adult. If a
     * passenger is aged under 18, you should specify their age instead of a type. A passenger can have only a type
     * or an age, but not both.
     */
    @JsonProperty("type")
    private PassengerType type;

    /**
     * The age of the passenger on the departure_date of the final slice. e.g. if you are searching for a round trip and
     * the passenger is 15 years old at the time of the outbound flight, but they then have their birthday and are
     * 16 years old for the inbound flight, you must set the age to 16. You should specify an age for passengers who
     * are under 18 years old. A passenger can have only a type or an age, but not both.
     */
    @JsonProperty("age")
    private Integer age;

    /**
     * The Loyalty Programme Accounts for this passenger
     */
    @JsonProperty("loyalty_programme_accounts")
    private List<LoyaltyProgrammeAccount> loyaltyProgrammeAccountList;

    @JsonProperty("id")
    private String id;

}
