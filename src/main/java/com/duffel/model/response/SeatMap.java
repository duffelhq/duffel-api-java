package com.duffel.model.response;

import com.duffel.model.response.seatmap.Cabin;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

/**
 * Seat map for a segment.
 */
@EqualsAndHashCode
@Getter
@ToString
public class SeatMap {

    /**
     * The list of cabins in this seat map.
     * Cabins are ordered by deck from lowest to highest, and then within each deck from the front to back of the
     * aircraft.
     */
    @JsonProperty("cabins")
    private List<Cabin> cabins;

    /**
     * Duffel's unique identifier for the seat map
     */
    @JsonProperty("id")
    private String id;

    /**
     * Duffel's unique identifier for the segment. It identifies the segment of an offer (i.e. the same segment
     * across offers will have different ids).
     */
    @JsonProperty("segment_id")
    private String segmentId;

    /**
     * Duffel's unique identifier for the slice. It identifies the slice of an offer (i.e. the same slice across
     * offers will have different ids.)
     */
    @JsonProperty("slice_id")
    private String sliceId;

}
