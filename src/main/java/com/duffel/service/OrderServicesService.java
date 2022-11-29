package com.duffel.service;

import com.duffel.model.request.OrderChangeServices;
import com.duffel.model.request.PostData;
import com.duffel.model.response.Order;
import com.duffel.model.response.ServiceCollection;
import com.duffel.model.response.Service;
import com.duffel.net.ApiClient;

import java.util.List;

public class OrderServicesService extends PostResource<Order, ServiceCollection> {

    private static final String ENDPOINT = "/air/orders";

    public OrderServicesService(ApiClient apiClient) {
        super(apiClient, ENDPOINT);
    }

    public Order post(OrderChangeServices request) {
        return super.post(Order.class, request.getOrderId() + "/services", new PostData<>(request)).getData();
    }

    public List<Service> get(String id) {
        return super.get(ServiceCollection.class, "/" + id + "/available_services").getData();
    }

}
