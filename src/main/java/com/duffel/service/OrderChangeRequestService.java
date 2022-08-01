package com.duffel.service;

import com.duffel.model.request.OrderChange;
import com.duffel.model.request.PostData;
import com.duffel.model.response.orderchange.OrderChangeResponse;
import com.duffel.net.ApiClient;

public class OrderChangeRequestService extends PostResource<OrderChangeResponse, OrderChangeResponse> {

    private static final String ENDPOINT = "/air/order_change_requests";

    public OrderChangeRequestService(ApiClient apiClient) {
        super(apiClient, ENDPOINT);
    }

    public OrderChangeResponse post(OrderChange request) {
        return super.post(OrderChangeResponse.class, new PostData<>(request)).getData();
    }

    public OrderChangeResponse getById(String id) {
        return super.getById(OrderChangeResponse.class, id).getData();
    }
}
