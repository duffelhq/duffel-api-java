package com.duffel.model.response.seatmap;

import com.duffel.model.response.seatmap.element.Element;
import com.duffel.model.response.seatmap.element.GenericElement;
import com.duffel.model.response.seatmap.element.SeatElement;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@EqualsAndHashCode
@Getter
@ToString
public class RowSection {

    /// <summary>
    /// The elements that make up this section
    /// </summary>
    @JsonProperty("elements")
    @JsonTypeInfo(
            use = JsonTypeInfo.Id.NAME,
            property = "type",
            defaultImpl = GenericElement.class,
            visible = true
    )
    @JsonSubTypes({
            @JsonSubTypes.Type(value = SeatElement.class, name = "seat")
    })
    private List<Element> elements;

}
