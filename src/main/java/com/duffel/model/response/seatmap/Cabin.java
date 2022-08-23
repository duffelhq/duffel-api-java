package com.duffel.model.response.seatmap;

import com.duffel.model.CabinClass;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

/**
 * Airplane cabin.
 */
@EqualsAndHashCode
@Getter
@ToString
public class Cabin {

    /**
     * The number of aisles in this cabin.
     * If this is set to 1, each row of the cabin is split into two sections. If this is set to 2, each row of the cabin is split into three sections.
     */
    @JsonProperty("aisles")
    private Integer aisles;

    /**
     * The cabin class that the passenger will travel in on this segment
     * Possible values: "first", "business", "premium_economy", or "economy"
     */
    @JsonProperty("cabin_class")
    private CabinClass cabinClass;

    /**
     * Level 0 is the main deck and level 1 is the upper deck above that, which is found on some large aircraft.
     */
    @JsonProperty("deck")
    public Integer deck;

    /**
     * A list of rows in this cabin.
     * Row sections are broken up by aisles. Rows are ordered from front to back of the aircraft.
     */
    @JsonProperty("rows")
    private List<Row> rows;

    /**
     * Where the wings of the aircraft are in relation to rows in the cabin.
     * The numbers correspond to the indices of the first and the last row which are overwing. You can use this to
     * draw a visual representation of the wings to help users get a better idea of what they will see outside their
     * window.
     * The indices are 0th-based and are for all rows, not just those that have seats.
     * This is null when no rows of the cabin are overwing.
     */
    @JsonProperty("wings")
    private Wings wings;

}
