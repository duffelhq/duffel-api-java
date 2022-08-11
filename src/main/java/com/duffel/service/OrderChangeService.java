package com.duffel.service;

import com.duffel.model.request.OrderChangePaymentRequest;
import com.duffel.model.request.PendingOrderChange;
import com.duffel.model.request.PostData;
import com.duffel.model.response.orderchange.OrderChangeOffer;
import com.duffel.net.ApiClient;

public class OrderChangeService extends PostResource<OrderChangeOffer, OrderChangeOffer> {

    private static final String ENDPOINT = "/air/order_changes";

    public OrderChangeService(ApiClient apiClient) {
        super(apiClient, ENDPOINT);
    }

    public OrderChangeOffer post(PendingOrderChange request) {
        return super.post(OrderChangeOffer.class, new PostData<>(request)).getData();
    }

    public OrderChangeOffer getById(String id) {
        return super.getById(OrderChangeOffer.class, id).getData();
    }

    public OrderChangeOffer confirm(String orderChangeId, OrderChangePaymentRequest paymentRequest) {
        return super.post(OrderChangeOffer.class, orderChangeId + "/actions/confirm", new PostData<>(paymentRequest)).getData();
    }
}
