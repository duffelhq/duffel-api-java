package com.duffel.model.response.seatmap.element;

import com.duffel.model.response.seatmap.SeatService;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

/**
 * Seat map seat element, with properties and services.
 */
@EqualsAndHashCode(callSuper = true)
@Getter
@ToString
public class SeatElement extends Element {

    /**
     * Seats are considered a special kind of service. There will be at most one service per seat per passenger. A
     * seat can only be booked for one passenger. If a seat has no available services (which will be represented as
     * an empty list : []) then it's unavailable.
     */
    @JsonProperty("available_services")
    private List<SeatService> availableServices;

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
    private String elementName;

}
