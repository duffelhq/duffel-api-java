package com.duffel.model.response;

import com.duffel.model.response.seatmap.Cabin;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@EqualsAndHashCode
@Getter
@ToString
public class SeatMap {

    ///  <summary>
    ///  The list of cabins in this seat map.
    ///  Cabins are ordered by deck from lowest to highest, and then within each deck from the front to back of the
    ///  aircraft.
    ///  </summary>
    @JsonProperty("cabins")
    private List<Cabin> cabins;

    ///  <summary>
    ///  Duffel's unique identifier for the seat map
    ///  </summary>
    @JsonProperty("id")
    private String id;

    ///  <summary>
    ///  Duffel's unique identifier for the segment. It identifies the segment of an offer (i.e. the same segment
    ///  across offers will have different ids).
    ///  </summary>
    @JsonProperty("segment_id")
    private String segmentId;

    ///  <summary>
    ///  Duffel's unique identifier for the slice. It identifies the slice of an offer (i.e. the same slice across
    ///  offers will have different ids.)
    ///  </summary>
    @JsonProperty("slice_id")
    private String sliceId;

}
