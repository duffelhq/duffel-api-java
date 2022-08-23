package com.duffel.model.request;

import com.duffel.model.Data;
import com.duffel.model.WebhookEvent;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * Create request for a new webhook.
 */
@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@ToString
public class WebhookCreateRequest extends Data<WebhookCreateRequest> {

    /**
     * The events that this webhook will be subscribed to
     * Example: ["order.created","order.airline_initiated_change_detected"]
     */
    @JsonProperty("events")
    private List<WebhookEvent> events;

    /**
     * The URL where your webhook will be received
     */
    @JsonProperty("url")
    private String url;

}
