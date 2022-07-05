package com.duffel.model.response.seatmap;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@EqualsAndHashCode
@Getter
@ToString
public class Wings {

    ///  <summary>
    ///  The index of the first row which is overwing, starting from the front of the aircraft.
    ///  </summary>
    @JsonProperty("first_row_index")
    private Integer firstRowIndex;

    ///  <summary>
    ///  The index of the last row which is overwing, starting from the front of the aircraft.
    ///  </summary>
    @JsonProperty("last_row_index")
    private Integer lastRowIndex;

}
