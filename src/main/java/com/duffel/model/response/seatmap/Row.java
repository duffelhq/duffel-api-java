package com.duffel.model.response.seatmap;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

/**
 * Seat map row of elements.
 */
@EqualsAndHashCode
@Getter
@ToString
public class Row {

    /**
    * A list of sections.
    * Each row is divided into sections by one or more aisles.
    */
    @JsonProperty("sections")
    private List<RowSection> sections;

}
