package com.duffel.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Airline extends Data<Airline> {

    ///  <summary>
    ///  The two-character IATA code for the airline. This may be null for non-IATA carriers.
    ///  </summary>
    @JsonProperty("iata_code")
    public String iataCode;

    ///  <summary>
    ///  Duffel's unique identifier for the airline
    ///  </summary>
    @JsonProperty("id")
    public String id;

    ///  <summary>
    ///  The name of the airline
    ///  </summary>
    @JsonProperty("name")
    public String name;

    @Override
    public String toString() {
        return "Airline{" +
                "iataCode='" + iataCode + '\'' +
                ", id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
