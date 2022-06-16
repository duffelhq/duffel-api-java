package com.duffel.service;

import com.duffel.model.request.OrderRequest;
import com.duffel.model.request.OrderUpdate;
import com.duffel.model.request.PostData;
import com.duffel.model.response.Order;
import com.duffel.net.ApiClient;

public class OrderService extends PostResource<Order> {

    private static final String ENDPOINT = "/air/orders";

    public OrderService(ApiClient apiClient) {
        super(apiClient, ENDPOINT);
    }

    public Order post(OrderRequest request) {
        return super.post(Order.class, new PostData<>(request)).getData();
    }

    public Order getById(String id) {
        return super.getById(Order.class, id).getData();
    }

    public Order update(String id, OrderUpdate update) {
        return super.update(Order.class, id, new PostData<>(update)).getData();
    }

}
