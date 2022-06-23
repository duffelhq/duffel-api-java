package com.duffel.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@EqualsAndHashCode
@Getter
@ToString
public class Location {

    ///  <summary>
    ///  The name of the city (or cities separated by a /) where the airport is located
    ///  </summary>
    @JsonProperty("city_name")
    private String city_name;

    ///  <summary>
    ///  The 3-letter IATA code for the city where the place is located. Only present for airports which are registered
    ///  with IATA as belonging to a metropolitan area.
    ///  </summary>
    @JsonProperty("iata_city_code")
    private String iataCityCode;

    ///  <summary>
    ///  The 3-letter IATA code for the place
    ///  </summary>
    @JsonProperty("iata_code")
    private String iataCode;

    ///  <summary>
    ///  The ISO 3166-1 alpha-2 code for the country where the city is located
    ///  </summary>
    @JsonProperty("iata_country_code")
    private String iataCountryCode;

    ///  <summary>
    ///  The four-character ICAO code for the airport
    ///  </summary>
    @JsonProperty("icao_code")
    private String icaoCode;

    ///  <summary>
    ///  Duffel's unique identifier for the place
    ///  </summary>
    @JsonProperty("id")
    private String id;

    ///  <summary>
    ///  The latitude position of the airport represented in Decimal degrees with 6 decimal points with a range
    ///  between -90° and 90°
    ///  </summary>
    @JsonProperty("latitude")
    private Double latitude;

    ///  <summary>
    ///  The longitude position of the airport represented in Decimal degrees with 6 decimal points with a range
    ///  between -180° and 180°
    ///  </summary>
    @JsonProperty("longitude")
    private Double longitude;

    ///  <summary>
    ///  The name of the place
    ///  </summary>
    @JsonProperty("name")
    private String name;

    ///  <summary>
    ///  The time zone of the airport, specified by name from the tz database
    ///  </summary>
    @JsonProperty("time_zone")
    private String timeZone;

    ///  <summary>
    ///  The type of the place
    ///  </summary>
    @JsonProperty("type")
    private LocationType type;

}
