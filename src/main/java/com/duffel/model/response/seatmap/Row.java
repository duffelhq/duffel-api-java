package com.duffel.model.response.seatmap;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@EqualsAndHashCode
@Getter
@ToString
public class Row {

    /// <summary>
    /// A list of sections.
    /// Each row is divided into sections by one or more aisles.
    /// </summary>
    @JsonProperty("sections")
    private List<RowSection> sections;

}
