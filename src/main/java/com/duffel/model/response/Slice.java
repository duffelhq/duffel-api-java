package com.duffel.model.response;

import com.duffel.model.Location;
import com.duffel.model.LocationType;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@EqualsAndHashCode
@Getter
@ToString
public class Slice {

    @JsonProperty("created_at")
    private LocalDateTime createdAt;

    @JsonProperty("origin")
    private Location origin;

    @JsonProperty("origin_type")
    private LocationType originType;

    @JsonProperty("destination")
    private Location destination;

    @JsonProperty("destination_type")
    private LocationType destinationType;

    @JsonProperty("departure_date")
    private String departureDate;

}
