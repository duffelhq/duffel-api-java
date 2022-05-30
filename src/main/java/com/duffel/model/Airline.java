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
    ///  The name of the airline
    ///  </summary>
    @JsonProperty("name")
    private String name;

}
