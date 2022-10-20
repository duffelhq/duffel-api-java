package com.duffel.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Private fare code, for retrieving agreed custom fares and providing business codes.
 */
@EqualsAndHashCode
@Getter
@Setter
@ToString
public class PrivateFare {

    /**
     * Airline provided corporate code.
     */
    @JsonProperty("corporate_code")
    private String corporateCode;

    /**
     * Reference to identify the business
     * e.g. ABN:2345678
     */
    @JsonProperty("tracking_reference")
    private String trackingReference;

}
