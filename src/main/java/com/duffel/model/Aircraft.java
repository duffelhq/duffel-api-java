package com.duffel.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@Getter
@ToString
public class Aircraft extends Data<Aircraft> {

    ///  <summary>
    ///  The name of the aircraft
    ///  </summary>
    @JsonProperty("name")
    private String name;

    ///  <summary>
    ///  Duffel's unique identifier for the aircraft
    ///  </summary>
    @JsonProperty("id")
    private String id;

    ///  <summary>
    ///  The three-character IATA code for the aircraft
    ///  </summary>
    @JsonProperty("iata_code")
    private String iataCode;

}
