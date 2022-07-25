package com.duffel.service;

import com.duffel.model.OrderCancellationCollection;
import com.duffel.model.request.OrderCancellationRequest;
import com.duffel.model.request.PostData;
import com.duffel.model.response.OrderCancellation;
import com.duffel.net.ApiClient;

public class OrderCancellationService extends PostResource<OrderCancellationRequest, OrderCancellationCollection> {

    private static final String ENDPOINT = "/air/order_cancellations";

    public OrderCancellationService(ApiClient apiClient) {
        super(apiClient, ENDPOINT);
    }

    public OrderCancellation post(OrderCancellationRequest request) {
        return super.post(OrderCancellationRequest.class, new PostData<>(request)).getData();
    }

    public OrderCancellation getById(String id) {
        return super.getById(OrderCancellationRequest.class, id).getData();
    }

    public OrderCancellationCollection getPage(String before, String after, Integer limit) {
        return super.getPage(OrderCancellationCollection.class, "", before, after, limit);
    }

    public OrderCancellation confirm(String id) {
        return super.post(OrderCancellationRequest.class, id + "/actions/confirm", new PostData<>(null)).getData();
    }

}
