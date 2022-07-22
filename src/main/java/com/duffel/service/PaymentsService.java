package com.duffel.service;

import com.duffel.model.request.PaymentRequest;
import com.duffel.model.request.PostData;
import com.duffel.model.response.Payment;
import com.duffel.net.ApiClient;

public class PaymentsService extends PostResource<Payment, Payment> {

    private static final String ENDPOINT = "/air/payments";

    public PaymentsService(ApiClient apiClient) {
        super(apiClient, ENDPOINT);
    }

    public Payment create(PaymentRequest request) {
        return super.post(Payment.class, new PostData<>(request));
    }

}
