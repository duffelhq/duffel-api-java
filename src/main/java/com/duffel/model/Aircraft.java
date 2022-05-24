package com.duffel.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Aircraft extends Data<Aircraft> {

    ///  <summary>
    ///  The name of the aircraft
    ///  </summary>
    @JsonProperty("name")
    public String name;

    ///  <summary>
    ///  Duffel's unique identifier for the aircraft
    ///  </summary>
    @JsonProperty("id")
    public String id;

    ///  <summary>
    ///  The three-character IATA code for the aircraft
    ///  </summary>
    @JsonProperty("iata_code")
    public String iataCode;

    @Override
    public String toString() {
        return "Aircraft{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", iataCode='" + iataCode + '\'' +
                '}';
    }
}
