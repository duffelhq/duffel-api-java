package com.duffel.service;

import com.duffel.model.request.OfferRequest;
import com.duffel.model.request.PostData;
import com.duffel.model.response.OfferResponse;
import com.duffel.net.ApiClient;

public class OfferRequestService extends PostResource<OfferResponse> {

    private static final String ENDPOINT = "/air/offer_requests";

    public OfferRequestService(ApiClient apiClient) {
        super(apiClient, ENDPOINT);
    }

    public OfferResponse post(OfferRequest request) {
        return super.post(OfferResponse.class, new PostData<>(request)).getData();
    }

    public OfferResponse getById(String id) {
        return super.getById(OfferResponse.class, id).getData();
    }
}
