package com.duffel.model;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Webhook event types.
 */
public enum WebhookEvent {

    CREATED("order.created"),
    AIC_DETECTED("order.airline_initiated_change_detected");

    private final String eventType;

    /**
     * Event type.
     * @param eventType type
     */
    WebhookEvent(String eventType) {
        this.eventType = eventType;
    }

    /**
     * Get the event type.
     * @return event type
     */
    @Override
    @JsonValue
    public String toString() {
        return eventType;
    }
}
