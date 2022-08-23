package com.duffel.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Webhook information.
 */
@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@ToString
public class Webhook extends Data<Webhook> {

    /**
     * Whether the webhook receiver is actively being notified or not
     */
    @JsonProperty("active")
    private boolean active;

    /**
     * The ISO 8601 datetime at which the order change was created
     * Example: "2020-04-11T15:48:11.642Z"
     */
    @JsonProperty("created_at")
    private LocalDateTime createdAt;

    /**
     * The events that this webhook will be subscribed to
     * Example: ["order.created","order.airline_initiated_change_detected"]
     */
    @JsonProperty("events")
    private List<WebhookEvent> events;

    /**
     * Duffel's unique identifier for the webhook receiver
     * Example: "end_0000A3tQSmKyqOrcySrGbo"
     */
    @JsonProperty("id")
    private String id;

    /**
     * The live mode that the webhook was created in. It will only receive events for that same live mode. For
     * example, you won't receive "order.created" events for orders that you created in the sandbox, if your webhook is
     * for live_mode: true.
     * Example: true
     */
    @JsonProperty("live_mode")
    private boolean liveMode;

    /**
     * The ISO 8601 datetime at which the order change was updated
     * Example: "2020-04-11T15:48:11.642Z"
     */
    @JsonProperty("updated_at")
    private LocalDateTime updatedAt;

    /**
     * The URL where your webhook will be received
     * Example: "https://www.example.com:4000/webhooks"
     */
    @JsonProperty("url")
    private String url;

}
