package com.duffel.service;

import com.duffel.model.OfferCollection;
import com.duffel.model.response.Offer;
import com.duffel.net.ApiClient;

public class OfferService extends Resource<Offer, OfferCollection, Offer> {

    private static final String ENDPOINT = "/air/offers";

    public OfferService(ApiClient apiClient) {
        super(apiClient, ENDPOINT);
    }

    public Offer getById(String id, boolean return_available_services) {
        String services = return_available_services ? "?return_available_services=true" : "";
        return super.getById(Offer.class, id + services).getData();
    }

    public OfferCollection getPage(String offerRequestId, String before, String after, Integer limit) {
        return super.getPage(OfferCollection.class, "&offer_request_id=" + offerRequestId, before, after, limit);
    }
}
