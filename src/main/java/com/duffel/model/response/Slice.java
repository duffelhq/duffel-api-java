package com.duffel.model.response;

import com.duffel.model.Conditions;
import com.duffel.model.Location;
import com.duffel.model.LocationType;
import com.duffel.model.response.offer.Segment;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.time.Duration;
import java.util.List;

/**
 * Flight slice information.
 */
@EqualsAndHashCode
@Getter
@ToString
public class Slice {

    /**
     * The conditions associated with this slice, describing the kinds of modifications you can make post-booking and
     * any penalties that will apply to those modifications.
     * This condition is applied only to this slice and to all the passengers associated with this offer.
     * If a particular kind of modification is allowed, you may not always be able to take action through the Duffel
     * API.
     * In some cases, you may need to contact the Duffel support team or the airline directly.
     */
    @JsonProperty("conditions")
    private Conditions conditions;

    /**
     * The City or Airport where this slice begins.
     */
    @JsonProperty("origin")
    private Location origin;

    /**
     * The type of the origin
     */
    @JsonProperty("origin_type")
    private LocationType originType;

    /**
     * The City or Airport where this slice ends.
     */
    @JsonProperty("destination")
    private Location destination;

    /**
     * The type of the destination
     */
    @JsonProperty("destination_type")
    private LocationType destinationType;

    /**
     * The duration of the slice, represented as a ISO 8601 duration
     */
    @JsonProperty("duration")
    private Duration duration;

    /**
     * The name of the fare brand associated with this slice.
     * A fare brand specifies the travel conditions you get on your slice made available by the airline.
     * e.g. a British Airways Economy Basic fare will only include a hand baggage allowance.
     * It is worth noting that the fare brand names are defined by the airlines themselves, and therefore
     * they are subject to change without any prior notice.
     * We're in the process of adding support for fare_brand_name across all our airlines, so for now, this field
     * may be null in some offers. This will become a non-nullable attribute in the near future.
     */
    @JsonProperty("fare_brand_name")
    private String fareBrandName;

    /**
     * Duffel's unique identifier for the slice. It identifies the slice of an offer
     * (i.e. the same slice across offers will have different ids.)
     */
    @JsonProperty("id")
    private String id;

    /**
     * The segments - that is, specific flights - that the airline is offering to get the passengers from the origin
     * to the destination
     */
    @JsonProperty("segments")
    private List<Segment> segments;

}
