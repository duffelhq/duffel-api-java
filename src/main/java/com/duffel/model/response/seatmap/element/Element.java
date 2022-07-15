package com.duffel.model.response.seatmap.element;

import com.duffel.model.response.seatmap.ElementType;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@EqualsAndHashCode
@Getter
@ToString
public abstract class Element {

    ///  <summary>
    ///  The type of this element
    ///  </summary>
    @JsonProperty("type")
    private ElementType type;

}
