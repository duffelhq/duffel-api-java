package com.duffel.model.response.order.metadata;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

/**
 * Seat specifications.
 */
@EqualsAndHashCode
@Getter
@ToString
public class SeatMetadata implements Metadata {

    /**
     * The designator used to uniquely identify the seat, usually made up of a row number and a column letter
     */
    @JsonProperty("designator")
    private String designator;

    /**
     * Each disclosure is text, in English, provided by the airline that describes the terms and conditions of this
     * seat. We recommend showing this in your user interface to make sure that customers understand any restrictions
     * and limitations.
     */
    @JsonProperty("disclosures")
    private List<String> disclosures;

    /**
     * A name which describes the type of seat, which you can display in your user interface to help customers to
     * understand its features
     */
    @JsonProperty("name")
    private String name;

}
