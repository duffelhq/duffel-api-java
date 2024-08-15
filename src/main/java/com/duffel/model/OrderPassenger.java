package com.duffel.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * Passenger details for an order passenger.
 */
@EqualsAndHashCode
@Getter
@Setter
@ToString
public class OrderPassenger {

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
     * used to make this association. It should contain the id of the infant passenger as returned within the
     * OffersRequest.
     */
    @JsonProperty("infant_passenger_id")
    private String infantPassengerId;

    /**
     * The passenger's identity documents. You may only provide one identity document per passenger. The identity
     * document's type must be included in the offer's allowed_passenger_identity_document_types. If the offer's
     * passenger_identity_documents_required is set to true, then an identity document must be provided.
     */
    @JsonProperty("identity_documents")
    private List<IdentityDocument> identityDocuments;

    /**
     * The id of the passenger, returned when the OffersRequest was created
     */
    @JsonProperty("id")
    private String id;

    /**
     * The passenger's gender
     */
    @JsonProperty("gender")
    private String gender;

    /**
     * The passenger's given name. Only space, -, ', and letters from the ASCII, Latin-1 Supplement and Latin Extended-A (with the exceptions of �, �, 2, 3, R, S, �, and �) Unicode charts are accepted. All other characters will result in a validation error. The minimum length is 1 character, and the maximum is 20 characters.
     */
    @JsonProperty("given_name")
    private String givenName;

    /**
     * The passenger's family name. Only space, -, ', and letters from the ASCII, Latin-1 Supplement and Latin Extended-A (with the exceptions of �, �, 2, 3, R, S, �, and �) Unicode charts are accepted. All other characters will result in a validation error. The minimum length is 1 character, and the maximum is 20 characters.
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

    @EqualsAndHashCode
    @Getter
    @Setter
    @ToString
    public static class IdentityDocument {

        /**
         * The type of the identity document. Currently, the only supported types are passport, tax_id, known_traveler_number, and passenger_redress_number. The identity document's type supported by the airline can be found in the offer's supported_passenger_identity_document_types.
         */
        @JsonProperty("type")
        private IdentityDocumentType identityDocumentType;

        /**
         * Must only be provided for passport type. The date on which the identity document expires
         */
        @JsonProperty("expires_on")
        private String expiresOn;

        /**
         * Must only be provided for passport, known_traveler_number, and passenger_redress_number types. The ISO 3166-1 alpha-2 code of the country that issued this identity document
         */
        @JsonProperty("issuing_country_code")
        private String issuingCountryCode;

        /**
         * The unique identifier of the identity document. e.g. the passport number.
         */
        @JsonProperty("unique_identifier")
        private String uniqueIdentifier;

    }

}
