package com.duffel.model.response.seatmap;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * Wing locations on an airplane.
 */
@EqualsAndHashCode
@Getter
@ToString
public class Wings {

    /**
     * The index of the first row which is overwing, starting from the front of the aircraft.
     */
    @JsonProperty("first_row_index")
    private Integer firstRowIndex;

    /**
     * The index of the last row which is overwing, starting from the front of the aircraft.
     */
    @JsonProperty("last_row_index")
    private Integer lastRowIndex;

}
