package com.duffel.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * Airline information.
 */
@EqualsAndHashCode(callSuper = true)
@Getter
@ToString
public class Airline extends Data<Airline> {

    /**
     * The two-character IATA code for the airline. This may be null for non-IATA carriers.
     */
    @JsonProperty("iata_code")
    private String iataCode;

    /**
     * Duffel's unique identifier for the airline
     */
    @JsonProperty("id")
    private String id;

    /**
    *  Path to a svg of the airline lockup logo. A lockup logo is also called a combination logo, in which it combines
    *  the logotype and logomark. This may be null if no logo is available.
    *  Example: "https://assets.duffel.com/img/airlines/for-light-background/full-color-lockup/BA.svg"
    */
    @JsonProperty("logo_lockup_url")
    private String logoLockupUrl;

    /**
    *  Path to a svg of the airline logo. This may be null if no logo is available.
    *  Example: "https://assets.duffel.com/img/airlines/for-light-background/full-color-logo/BA.svg"
    */
    @JsonProperty("logo_symbol_url")
    private String logoSymbolUrl;

    /**
     * The name of the airline
     */
    @JsonProperty("name")
    private String name;

}
