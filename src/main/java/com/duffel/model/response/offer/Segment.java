package com.duffel.model.response.offer;

import com.duffel.model.Aircraft;
import com.duffel.model.Airline;
import com.duffel.model.Location;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Segment of a flight.
 */
@EqualsAndHashCode
@Getter
@ToString
public class Segment {

    /**
     * Additional segment-specific information about the passengers included in the offer (e.g. their baggage allowance and the cabin class they will be travelling in)
     */
    @JsonProperty("passengers")
    private List<SegmentPassenger> passengers;

    /**
     * The terminal at the origin airport from which the segment is scheduled to depart
     * Nullable
     */
    @JsonProperty("origin_terminal")
    private String originTerminal;

    /**
     * The airport from which the flight is scheduled to depart
     */
    @JsonProperty("origin")
    private Location origin;

    /**
     * The flight number assigned by the OperatingCarrier.
     * This may not be present, in which case you should display the MarketingCarrier's information and the
     * MarketingCarrierFlightNumber, and simply state the name of the operating_carrier.
     */
    @JsonProperty("operating_carrier_flight_number")
    private String operatingCarrierFlightNumber;

    /**
     * The airline actually operating this segment. This may differ from the MarketingCarrier in the case of a
     * "codeshare", where one airline sells flights operated by another airline.
     */
    @JsonProperty("operating_carrier")
    private Airline operatingCarrier;

    /**
     * The flight number assigned by the marketing carrier
     */
    @JsonProperty("marketing_carrier_flight_number")
    private String marketingCarrierFlightNumber;

    /**
     * The airline selling the tickets for this segment.
     * This may differ from the OperatingCarrier in the case of a "codeshare", where one airline sells flights
     * operated by another airline.
     */
    @JsonProperty("marketing_carrier")
    private Airline marketingCarrier;

    /**
     * Duffel's unique identifier for the segment. It identifies the segment of an offer (i.e. the same segment across
     * offers will have different ids).
     */
    @JsonProperty("id")
    private String id;

    /**
     * The duration of the segment, represented as a ISO 8601 duration
     */
    @JsonProperty("duration")
    private Duration duration;

    /**
     * The distance of the segment in kilometres
     */
    @JsonProperty("distance")
    private String distance;

    /**
     * The terminal at the Destination Airport where the segment is scheduled to
     */
    @JsonProperty("destination_terminal")
    private String destinationTerminal;

    /**
     * The terminal at the Destination Airport where the segment is scheduled to
     */
    @JsonProperty("destination")
    private Location destination;

    /**
     * The ISO 8601 datetime at which the segment is scheduled to depart, in the origin airport timezone
     * (see origin.timezone)
     */
    @JsonProperty("departing_at")
    private LocalDateTime departingAt;

    /**
     * The ISO 8601 datetime at which the segment is scheduled to arrive, in the destination airport timezone
     * (see destination.timezone)
     */
    @JsonProperty("arriving_at")
    private LocalDateTime arrivingAt;

    /**
     * The aircraft that the operating carrier will use to operate this segment
     */
    @JsonProperty("aircraft")
    private Aircraft aircraft;

}
