package com.duffel.model;

import com.duffel.model.response.ChangeSlice;
import com.duffel.model.response.order.OrderSlice;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Getter
@ToString
public class AirlineInitiatedChange extends Data<AirlineInitiatedChange> {

    ///  <summary>
    ///  The action taken in response to this airline-initiated change.
    ///
    ///  Accepted, cancelled and changed reflect your action in accepting the change, cancelling the order or
    ///  changing the order respectively.
    ///  </summary>
    @JsonProperty("action_taken")
    private AirlineInitiatedChangeActionTaken actionTaken;

    ///  <summary>
    ///  The ISO 8601 datetime at which an action was taken
    ///  </summary>
    @JsonProperty("action_taken_at")
    private LocalDateTime actionTakenAt;

    ///  <summary>
    ///  List of updated slices and segments following the change. These slices and segments may each have a new ID
    ///  as a result of the changes.
    ///  </summary>
    @JsonProperty("added")
    private List<ChangeSlice> added;

    ///  <summary>
    ///  The available actions you can take on this Airline-Initiated Change through our API.
    ///
    ///  "update" means that you can use the update endpoint for an Airline-Initiated Change.
    ///
    ///  Possible values in array: "accept", "cancel", "change", or "update"
    ///  Example: ["accept","cancel","change"]
    ///  </summary>
    @JsonProperty("available_actions")
    private List<AirlineInitiatedChangeActions> availableActions;

    ///  <summary>
    ///  The ISO 8601 datetime at which we detected the airline-initiated change
    ///  </summary>
    @JsonProperty("created_at")
    private LocalDateTime createdAt;

    ///  <summary>
    ///  Duffel's unique identifier for the airline-initiated change
    ///  </summary>
    @JsonProperty("id")
    private String id;

    ///  <summary>
    ///  Duffel's unique identifier for the order
    ///  </summary>
    @JsonProperty("order_id")
    private String orderId;

    ///  <summary>
    ///  List of slices and segments as they were before the change.
    ///  </summary>
    @JsonProperty("removed")
    private List<ChangeSlice> removed;

    ///  <summary>
    ///  The associated Travel Agent Ticket, if any, for this Airline-Initiated Change. This value will be present for
    ///  Airline-Initiated changes that take some time to be processed.
    ///  </summary>
    @JsonProperty("travel_agent_ticket")
    private String travel_agent_ticket;

    ///  <summary>
    ///  The ISO 8601 datetime at which the airline-initiated change was last updated
    ///  </summary>
    @JsonProperty("updated_at")
    private LocalDateTime updatedAt;

    public enum AirlineInitiatedChangeActions {
        accept,
        cancel,
        change,
        update
    }

}
