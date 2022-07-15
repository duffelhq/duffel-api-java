package com.duffel.model.response.seatmap.element;

import com.duffel.model.response.seatmap.SeatService;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Getter
@ToString
public class SeatElement extends Element {

    ///  <summary>
    ///  Seats are considered a special kind of service. There will be at most one service per seat per passenger. A
    ///  seat can only be booked for one passenger. If a seat has no available services (which will be represented as
    ///  an empty list : []) then it's unavailable.
    ///  </summary>
    @JsonProperty("available_services")
    private List<SeatService> availableServices;

    ///  <summary>
    ///  The designator used to uniquely identify the seat, usually made up of a row number and a column letter
    ///  </summary>
    @JsonProperty("designator")
    private String designator;

    ///  <summary>
    ///  Each disclosure is text, in English, provided by the airline that describes the terms and conditions of this
    ///  seat. We recommend showing this in your user interface to make sure that customers understand any restrictions
    ///  and limitations.
    ///  </summary>
    @JsonProperty("disclosures")
    private List<String> disclosures;

    ///  <summary>
    ///  A name which describes the type of seat, which you can display in your user interface to help customers to
    ///  understand its features
    ///  </summary>
    @JsonProperty("name")
    private String elementName;

}
