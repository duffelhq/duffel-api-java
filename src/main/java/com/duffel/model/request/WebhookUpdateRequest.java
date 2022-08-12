package com.duffel.model.request;

import com.duffel.model.Data;
import com.duffel.model.WebhookEvent;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@ToString
public class WebhookUpdateRequest extends Data<WebhookUpdateRequest> {

    ///  <summary>
    ///  The desired active status of the webhook
    ///  Example: true
    ///  </summary>
    @JsonProperty("active")
    private boolean active;

    ///  <summary>
    ///  The URL where your webhook will be received
    ///  Example: "https://example.com/duffel/webhook"
    ///  </summary>
    @JsonProperty("url")
    private String url;

}
