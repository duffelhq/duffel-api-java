package com.duffel.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

/**
 * Location details for a city or airport.
 */
@EqualsAndHashCode(callSuper = true)
@Getter
@ToString
public class Location extends Data<Location> {

    /**
     * The name of the city (or cities separated by a /) where the airport is located
     */
    @JsonProperty("city_name")
    private String cityName;

    /**
     * The 3-letter IATA code for the city where the place is located. Only present for airports which are registered
     * with IATA as belonging to a metropolitan area.
     */
    @JsonProperty("iata_city_code")
    private String iataCityCode;

    /**
     * The 3-letter IATA code for the place
     */
    @JsonProperty("iata_code")
    private String iataCode;

    /**
     * The ISO 3166-1 alpha-2 code for the country where the city is located
     */
    @JsonProperty("iata_country_code")
    private String iataCountryCode;

    /**
     * The four-character ICAO code for the airport
     */
    @JsonProperty("icao_code")
    private String icaoCode;

    /**
     * Duffel's unique identifier for the place
     */
    @JsonProperty("id")
    private String id;

    /**
     * The latitude position of the airport represented in Decimal degrees with 6 decimal points with a range
     * between -90° and 90°
     */
    @JsonProperty("latitude")
    private Double latitude;

    /**
     * The longitude position of the airport represented in Decimal degrees with 6 decimal points with a range
     * between -180° and 180°
     */
    @JsonProperty("longitude")
    private Double longitude;

    /**
     * The name of the place
     */
    @JsonProperty("name")
    private String name;

    /**
     * The time zone of the airport, specified by name from the tz database
     */
    @JsonProperty("time_zone")
    private String timeZone;

    /**
     * The type of the place
     */
    @JsonProperty("type")
    private LocationType type;

    /**
     * The metropolitan area where the airport is located. Only present for airports which are registered with IATA
     * as belonging to a metropolitan area.
     */
    @JsonProperty("city")
    private CityDetail city;

    /**
     * The airports associated to a city. This will only be provided where the type is city.
     */
    @JsonProperty("airports")
    private List<Location> airports;

    /**
     * Properties specific to a city.
     */
    @EqualsAndHashCode
    @Getter
    @ToString
    public static class CityDetail {

        /**
         * The three-character IATA code for the city
         */
        @JsonProperty("iata_code")
        private String iataCode;

        /**
         * The ISO 3166-1 alpha-2 code for the country where the city is located
         */
        @JsonProperty("iata_country_code")
        private String iataCountryCode;

        /**
         * Duffel's unique identifier for the city
         */
        @JsonProperty("id")
        private String id;

        /**
         * The name of the city
         */
        @JsonProperty("name")
        private String name;

    }

}
