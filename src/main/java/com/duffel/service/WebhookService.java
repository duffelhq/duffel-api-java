package com.duffel.service;

import com.duffel.model.Webhook;
import com.duffel.model.request.PostData;
import com.duffel.model.request.WebhookCreateRequest;
import com.duffel.model.request.WebhookUpdateRequest;
import com.duffel.net.ApiClient;

public class WebhookService extends PostResource<Webhook, Webhook> {

    private static final String ENDPOINT = "/air/webhooks";

    public WebhookService(ApiClient apiClient) {
        super(apiClient, ENDPOINT);
    }

    public Webhook post(WebhookCreateRequest request) {
        return super.post(Webhook.class, new PostData<>(request)).getData();
    }

    public Webhook patch(String webhookId, WebhookUpdateRequest request) {
        return super.patch(Webhook.class, webhookId, new PostData<>(request)).getData();
    }

    public void ping(String webhookId) {
        super.post(Webhook.class, webhookId + "/actions/ping", null);
    }

}
