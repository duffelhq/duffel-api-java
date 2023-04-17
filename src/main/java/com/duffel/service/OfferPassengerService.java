package com.duffel.service;

import com.duffel.model.OfferCollection;
import com.duffel.model.OfferPassenger;
import com.duffel.model.request.PostData;
import com.duffel.net.ApiClient;

public class OfferPassengerService extends PostResource<OfferPassenger, OfferCollection> {

    private static final String ENDPOINT = "/air/offers";

    public OfferPassengerService(ApiClient apiClient) {
        super(apiClient, ENDPOINT);
    }

    public OfferPassenger patch(String offerId, String offerPassengerId, OfferPassenger offerPassenger) {
        return super.patch(OfferPassenger.class, offerId + "/passengers/" + offerPassengerId, new PostData<>(offerPassenger)).getData();
    }
}
