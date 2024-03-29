package com.duffel.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Airline initiated change action taken.
 */
@EqualsAndHashCode
@Getter
@Setter
@ToString
public class AirlineInitiatedChangeUpdate {

    /**
     * The action taken in response to this airline-initiated change.
     * Accepted, cancelled and changed reflect your action in accepting the change, cancelling the order or changing
     * the order respectively.
     * Possible values: "accepted", "cancelled", or "changed"
     */
    @JsonProperty("action_taken")
    private AirlineInitiatedChangeActionTaken actionTaken;

}
