package com.duffel.model.response.orderchange;

import com.duffel.model.Location;
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
public class OrderChangeSlice {

    ///  <summary>
    ///  The city or airport where this slice ends
    ///  </summary>
    @JsonProperty("destination")
    private Location destination;

    @JsonProperty("duration")
    private Duration duration;

    ///  <summary>
    ///  Duffel's unique identifier for the slice. It identifies the slice of an order (i.e. the same slice across orders will have different ids.
    ///  </summary>
    @JsonProperty("id")
    private String id;

    ///  <summary>
    ///  The city or airport where this slice begins
    ///  </summary>
    @JsonProperty("origin")
    private Location origin;

    ///  <summary>
    ///  The segments - that is, specific flights - that the airline is offering to get the passengers from the origin to the destination
    ///  </summary>
    @JsonProperty("segments")
    private List<Segment> segments;

}
