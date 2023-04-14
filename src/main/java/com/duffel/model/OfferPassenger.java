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
public class OfferPassenger {

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
     * The Loyalty Programme Accounts for this passenger
     */
    @JsonProperty("loyalty_programme_accounts")
    private List<LoyaltyProgrammeAccount> loyaltyProgrammeAccountList;

}
