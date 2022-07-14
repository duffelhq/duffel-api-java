package com.duffel.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@Getter
@ToString
public class Airline extends Data<Airline> {

    ///  <summary>
    ///  The two-character IATA code for the airline. This may be null for non-IATA carriers.
    ///  </summary>
    @JsonProperty("iata_code")
    private String iataCode;

    ///  <summary>
    ///  Duffel's unique identifier for the airline
    ///  </summary>
    @JsonProperty("id")
    private String id;

    ///  <summary>
    ///  Path to a svg of the airline lockup logo. A lockup logo is also called a combination logo, in which it combines
    ///  the logotype and logomark. This may be null if no logo is available.
    ///  Example: "https://assets.duffel.com/img/airlines/for-light-background/full-color-lockup/BA.svg"
    ///  </summary>
    @JsonProperty("logo_lockup_url")
    private String logoLockupUrl;

    ///  <summary>
    ///  Path to a svg of the airline logo. This may be null if no logo is available.
    ///  Example: "https://assets.duffel.com/img/airlines/for-light-background/full-color-logo/BA.svg"
    ///  </summary>
    @JsonProperty("logo_symbol_url")
    private String logoSymbolUrl;

    ///  <summary>
    ///  The name of the airline
    ///  </summary>
    @JsonProperty("name")
    private String name;

}
