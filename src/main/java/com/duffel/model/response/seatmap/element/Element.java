package com.duffel.model.response.seatmap.element;

import com.duffel.model.response.seatmap.ElementType;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * Element of a seat map.
 */
@EqualsAndHashCode
@Getter
@ToString
public abstract class Element {

    /**
     * The type of this element
     */
    @JsonProperty("type")
    private ElementType type;

}
