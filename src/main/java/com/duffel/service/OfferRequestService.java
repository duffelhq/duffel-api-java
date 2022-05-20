package com.duffel.service;

import com.duffel.model.response.Offer;
import com.duffel.model.response.OfferResponse;
import com.duffel.model.request.OfferRequest;
import com.duffel.model.request.PostData;
import com.duffel.net.HttpClient;

import java.net.URISyntaxException;

public class OfferRequestService extends PostResource<OfferResponse> {

    private static final String ENDPOINT = "/offer_requests";

    public OfferRequestService(HttpClient httpClient) {
        super(httpClient, ENDPOINT);
    }

    public OfferResponse post(OfferRequest request) throws URISyntaxException {
        return super.post(OfferResponse.class, new PostData<>(request)).getData();
    }

    public OfferResponse getById(String id) throws URISyntaxException {
        return super.getById(OfferResponse.class, id).getData();
    }
}
