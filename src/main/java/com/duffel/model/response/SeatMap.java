package com.duffel.model.response;

import com.duffel.model.response.seatmap.Cabin;
import com.duffel.model.response.seatmap.ElementType;
import com.duffel.model.response.seatmap.element.SeatElement;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Seat map for a segment.
 */
@EqualsAndHashCode
@Getter
@ToString
public class SeatMap {

    /**
     * The list of cabins in this seat map.
     * Cabins are ordered by deck from lowest to highest, and then within each deck from the front to back of the
     * aircraft.
     */
    @JsonProperty("cabins")
    private List<Cabin> cabins;

    /**
     * Duffel's unique identifier for the seat map
     */
    @JsonProperty("id")
    private String id;

    /**
     * Duffel's unique identifier for the segment. It identifies the segment of an offer (i.e. the same segment
     * across offers will have different ids).
     */
    @JsonProperty("segment_id")
    private String segmentId;

    /**
     * Duffel's unique identifier for the slice. It identifies the slice of an offer (i.e. the same slice across
     * offers will have different ids.)
     */
    @JsonProperty("slice_id")
    private String sliceId;

    /**
     * Basic pretty print of a seat map. Doesn't cover everything, but just a start to help with development.
     * @return basic printable text seat map
     */
    public String prettyPrintSeatMap() {
        StringBuilder output = new StringBuilder();
        output.append("Seat map ").append(getId()).append("\n");

        cabins.forEach(cabin -> {
                    output.append("\tCabin class ").append(cabin.getCabinClass()).append("\n");
                    cabin.getRows().stream().map(row -> {
                                List<String> rowData = new ArrayList<>();
                                row.getSections().forEach(section -> {
                                            rowData.add(section.getElements().stream().map(element ->
                                                    {
                                                        if (ElementType.seat.equals(element.getType())) {
                                                            return ((SeatElement) element).getDesignator() + (((SeatElement) element).getAvailableServices().isEmpty() ? "❌" : "✅");
                                                        } else if (ElementType.galley.equals(element.getType())) {
                                                            return "🧑‍🍳";
                                                        } else if (ElementType.lavatory.equals(element.getType())) {
                                                            return "🚾";
                                                        } else if (ElementType.empty.equals(element.getType())) {
                                                            return " ";
                                                        } else if (ElementType.bassinet.equals(element.getType())) {
                                                            return "👶";
                                                        } else if (ElementType.exit_row.equals(element.getType())) {
                                                            return "🚪";
                                                        } else {
                                                            return null;
                                                        }
                                                    }
                                            ).filter(Objects::nonNull).collect(Collectors.joining()));
                                        }
                                );
                                return String.join("| |", rowData) + "\n";
                            }
                    ).forEach(output::append);
                }
        );

        return output.toString();
    }
}
