package com.duffel.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Location {

    @JsonProperty("city_name")
    public String city_name;

    @JsonProperty("iata_city_code")
    public String iataCityCode;

    @JsonProperty("iata_code")
    public String iataCode;

    @JsonProperty("iata_country_code")
    public String iataCountryCode;

    @JsonProperty("icao_code")
    public String icaoCode;

    @JsonProperty("id")
    public String id;

    @JsonProperty("latitude")
    public Double latitude;

    @JsonProperty("longitude")
    public Double longitude;

    @JsonProperty("name")
    public String name;

    @JsonProperty("time_zone")
    public String timeZone;

    @JsonProperty("type")
    public LocationType type;

    public enum LocationType {
        airport,
        city
    }

    @Override
    public String toString() {
        return "Location{" +
                "city_name='" + city_name + '\'' +
                ", iataCityCode='" + iataCityCode + '\'' +
                ", iataCode='" + iataCode + '\'' +
                ", iataCountryCode='" + iataCountryCode + '\'' +
                ", icaoCode='" + icaoCode + '\'' +
                ", id='" + id + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", name='" + name + '\'' +
                ", timeZone='" + timeZone + '\'' +
                ", type=" + type +
                '}';
    }
}
