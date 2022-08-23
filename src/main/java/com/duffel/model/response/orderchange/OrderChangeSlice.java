package com.duffel.model.response.orderchange;

import com.duffel.model.Location;
import com.duffel.model.response.offer.Segment;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.time.Duration;
import java.util.List;

/**
 * Slice of an order change.
 */
@EqualsAndHashCode
@Getter
@ToString
public class OrderChangeSlice {

    /**
     * The city or airport where this slice ends
     */
    @JsonProperty("destination")
    private Location destination;

    @JsonProperty("duration")
    private Duration duration;

    /**
     * Duffel's unique identifier for the slice. It identifies the slice of an order (i.e. the same slice across orders will have different ids.
     */
    @JsonProperty("id")
    private String id;

    /**
     * The city or airport where this slice begins
     */
    @JsonProperty("origin")
    private Location origin;

    /**
     * The segments - that is, specific flights - that the airline is offering to get the passengers from the origin to the destination
     */
    @JsonProperty("segments")
    private List<Segment> segments;

}
