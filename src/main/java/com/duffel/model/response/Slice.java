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

@EqualsAndHashCode
@Getter
@ToString
public class Slice {

    ///  <summary>
    ///  The conditions associated with this slice, describing the kinds of modifications you can make post-booking and any penalties that will apply to those modifications.
    ///  This condition is applied only to this slice and to all the passengers associated with this offer.
    ///  If a particular kind of modification is allowed, you may not always be able to take action through the Duffel API.
    ///  In some cases, you may need to contact the Duffel support team or the airline directly.
    ///  </summary>
    @JsonProperty("conditions")
    private Conditions conditions;

    ///  <summary>
    ///  The <see cref="City"/> or <see cref="Airport"/> where this slice begins.
    ///  </summary>
    @JsonProperty("origin")
    private Location origin;

    ///  <summary>
    ///  The type of the origin
    ///  <summary>
    @JsonProperty("origin_type")
    private LocationType originType;

    ///  <summary>
    ///  The <see cref="City"/> or <see cref="Airport"/> where this slice ends.
    ///  </summary>
    @JsonProperty("destination")
    private Location destination;

    ///  <summary>
    ///  The type of the destination
    ///  <summary>
    @JsonProperty("destination_type")
    private LocationType destinationType;

    ///  <summary>
    ///  The duration of the slice, represented as a ISO 8601 duration
    ///  </summary>
    @JsonProperty("duration")
    private Duration duration;

    ///  <summary>
    ///  The name of the fare brand associated with this slice.
    ///  A fare brand specifies the travel conditions you get on your slice made available by the airline.
    ///  e.g. a British Airways Economy Basic fare will only include a hand baggage allowance.
    ///  It is worth noting that the fare brand names are defined by the airlines themselves and therefore
    ///  they are subject to change without any prior notice.
    ///  We're in the process of adding support for fare_brand_name across all our airlines, so for now, this field
    ///  may be null in some offers. This will become a non-nullable attribute in the near future.
    ///  </summary>
    @JsonProperty("fare_brand_name")
    private String fareBrandName;

    ///  <summary>
    ///  Duffel's unique identifier for the slice. It identifies the slice of an offer
    ///  (i.e. the same slice across offers will have different ids.)
    ///  </summary>
    @JsonProperty("id")
    private String id;

    ///  <summary>
    ///  The segments - that is, specific flights - that the airline is offering to get the passengers from the origin
    ///  to the destination
    ///  </summary>
    @JsonProperty("segments")
    private List<Segment> segments;

}
