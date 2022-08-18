package com.duffel.model;

import com.fasterxml.jackson.annotation.JsonValue;

public enum WebhookEvent {

    CREATED("order.created"),
    AIC_DETECTED("order.airline_initiated_change_detected");

    private final String eventType;

    WebhookEvent(String eventType) {
        this.eventType = eventType;
    }

    @Override
    @JsonValue
    public String toString() {
        return eventType;
    }
}
