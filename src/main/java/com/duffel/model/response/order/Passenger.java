package com.duffel.model.response.order;

import com.duffel.model.Gender;
import com.duffel.model.PassengerType;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * Passenger details.
 */
@EqualsAndHashCode
@Getter
@ToString
public class Passenger {

    /**
     * The type of the passenger, See: PassengerType
     */
    @JsonProperty("type")
    private PassengerType passengerType;

    /**
     * The passenger's title
     */
    @JsonProperty("title")
    private String title;

    /**
     * The passenger's phone number in E.164 (international) format
     */
    @JsonProperty("phone_number")
    private String phoneNumber;

    /**
     * Infant passengers, with an age of 0 or 1, must be associated with an adult passenger. This field should be
     * used to make this association. It should contain the id of the infant passenger as returned in the
     * OffersRequest.
     */
    @JsonProperty("infant_passenger_id")
    private String infantPassengerId;

    /**
     * The id of the passenger, returned when the OffersRequest was created
     */
    @JsonProperty("id")
    private String id;

    /**
     * The passenger's gender
     */
    @JsonProperty("gender")
    private Gender gender;

    /**
     * The passenger's given name. Only space, -, ', and letters from the ASCII, Latin-1 Supplement and Latin
     * Extended-A (with the exceptions of �, �, 2, 3, R, S, �, and �) Unicode charts are accepted. All other
     * characters will result in a validation error. The minimum length is 1 character, and the maximum is 20
     * characters.
     */
    @JsonProperty("given_name")
    private String givenName;

    /**
     * The passenger's family name. Only space, -, ', and letters from the ASCII, Latin-1 Supplement and Latin
     * Extended-A (with the exceptions of �, �, 2, 3, R, S, �, and �) Unicode charts are accepted. All other
     * characters will result in a validation error. The minimum length is 1 character, and the maximum is 20
     * characters.
     */
    @JsonProperty("family_name")
    private String familyName;

    /**
     * The passenger's email address
     */
    @JsonProperty("email")
    private String email;

    /**
     * The passenger's date of birth
     */
    @JsonProperty("born_on")
    private String bornOn;

}
