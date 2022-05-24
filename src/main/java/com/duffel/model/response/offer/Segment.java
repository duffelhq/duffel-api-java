package com.duffel.model.response.offer;

import com.duffel.model.Aircraft;
import com.duffel.model.Airline;
import com.duffel.model.Location;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

public class Segment {

    ///  <summary>
    ///  Additional segment-specific information about the passengers included in the offer (e.g. their baggage allowance and the cabin class they will be travelling in)
    ///  </summary>
    @JsonProperty("passengers")
    public List<SegmentPassenger> passengers;

    ///  <summary>
    ///  The terminal at the origin airport from which the segment is scheduled to depart
    ///  Nullable
    ///  </summary>
    @JsonProperty("origin_terminal")
    public String originTerminal;

    ///  <summary>
    ///  The airport from which the flight is scheduled to depart
    ///  </summary>
    @JsonProperty("origin")
    public Location origin;

    ///  <summary>
    ///  The flight number assigned by the <see cref="OperatingCarrier"/>.
    ///  This may not be present, in which case you should display the <see cref="MarketingCarrier"/>'s information and the <see cref="MarketingCarrierFlightNumber"/>,
    ///  and simply state the name of the operating_carrier.
    ///  </summary>
    @JsonProperty("operating_carrier_flight_number")
    public String operatingCarrierFlightNumber;

    ///  <summary>
    ///  The airline actually operating this segment. This may differ from the <see cref="MarketingCarrier"/> in the case of a "codeshare", where one airline sells flights operated by another airline.
    ///  </summary>
    @JsonProperty("operating_carrier")
    public Airline operatingCarrier;

    ///  <summary>
    ///  The flight number assigned by the marketing carrier
    ///  </summary>
    @JsonProperty("marketing_carrier_flight_number")
    public String marketingCarrierFlightNumber;

    ///  <summary>
    ///  The airline selling the tickets for this segment.
    ///  This may differ from the <see cref="OperatingCarrier"/> in the case of a "codeshare", where one airline sells flights operated by another airline.
    ///  </summary>
    @JsonProperty("marketing_carrier")
    public Airline marketingCarrier;

    ///  <summary>
    ///  Duffel's unique identifier for the segment. It identifies the segment of an offer (i.e. the same segment across
    ///  offers will have different ids).
    ///  </summary>
    @JsonProperty("id")
    public String id;

    ///  <summary>
    ///  The duration of the segment, represented as a ISO 8601 duration
    ///  </summary>
    @JsonProperty("duration")
    public Duration duration;

    ///  <summary>
    ///  The distance of the segment in kilometres
    ///  </summary>
    @JsonProperty("distance")
    public String distance;

    ///  <summary>
    ///  The terminal at the <see cref="Destination"/>  <see cref="Airport"/> where the segment is scheduled to
    ///  </summary>
    @JsonProperty("destination_terminal")
    public String destinationTerminal;

    ///  <summary>
    ///  The terminal at the <see cref="Destination"/>  <see cref="Airport"/> where the segment is scheduled to
    ///  </summary>
    @JsonProperty("destination")
    public Location destination;

    ///  <summary>
    ///  The ISO 8601 datetime at which the segment is scheduled to depart, in the origin airport timezone
    ///  (see origin.timezone)
    ///  </summary>
    @JsonProperty("departing_at")
    public LocalDateTime departingAt;

    ///  <summary>
    ///  The ISO 8601 datetime at which the segment is scheduled to arrive, in the destination airport timezone
    ///  (see destination.timezone)
    ///  </summary>
    @JsonProperty("arriving_at")
    public LocalDateTime arrivingAt;

    ///  <summary>
    ///  The aircraft that the operating carrier will use to operate this segment
    ///  </summary>
    @JsonProperty("aircraft")
    public Aircraft aircraft;

    @Override
    public String toString() {
        return "Segment{" +
                "passengers=" + passengers +
                ", originTerminal='" + originTerminal + '\'' +
                ", origin=" + origin +
                ", operatingCarrierFlightNumber='" + operatingCarrierFlightNumber + '\'' +
                ", operatingCarrier=" + operatingCarrier +
                ", marketingCarrierFlightNumber='" + marketingCarrierFlightNumber + '\'' +
                ", marketingCarrier=" + marketingCarrier +
                ", id='" + id + '\'' +
                ", duration=" + duration +
                ", distance='" + distance + '\'' +
                ", destinationTerminal='" + destinationTerminal + '\'' +
                ", destination=" + destination +
                ", departingAt=" + departingAt +
                ", arrivingAt=" + arrivingAt +
                ", aircraft=" + aircraft +
                '}';
    }
}
