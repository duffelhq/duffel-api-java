package com.duffel.service;

import com.duffel.model.PagedData;
import com.duffel.model.request.PaymentRequest;
import com.duffel.model.request.PostData;
import com.duffel.model.response.PaymentResponse;
import com.duffel.net.ApiClient;

public class PaymentsService extends PostResource<PaymentResponse, PagedData<PaymentResponse>, PaymentResponse> {

    private static final String ENDPOINT = "/air/payments";

    public PaymentsService(ApiClient apiClient) {
        super(apiClient, ENDPOINT);
    }

    public PaymentResponse create(PaymentRequest request) {
        return super.post(PaymentResponse.class, new PostData<>(request)).getData();
    }

}
