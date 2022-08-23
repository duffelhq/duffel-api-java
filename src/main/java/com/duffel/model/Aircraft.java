package com.duffel.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * Aircraft information.
 */
@EqualsAndHashCode(callSuper = true)
@Getter
@ToString
public class Aircraft extends Data<Aircraft> {

    /**
     * The name of the aircraft
     */
    @JsonProperty("name")
    private String name;

    /**
     * Duffel's unique identifier for the aircraft
     */
    @JsonProperty("id")
    private String id;

    /**
     * The three-character IATA code for the aircraft
     */
    @JsonProperty("iata_code")
    private String iataCode;

}
