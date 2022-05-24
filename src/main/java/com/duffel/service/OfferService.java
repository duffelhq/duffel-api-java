package com.duffel.service;

import com.duffel.model.OfferCollection;
import com.duffel.model.response.Offer;
import com.duffel.net.HttpClient;

public class OfferService extends Resource<Offer, OfferCollection> {

    private static final String ENDPOINT = "/air/offers";

    public OfferService(HttpClient httpClient) {
        super(httpClient, ENDPOINT);
    }

    public OfferCollection page(String offerRequestId, String before, String after, Integer limit) {
        return super.page(OfferCollection.class, "&offer_request_id=" + offerRequestId, before, after, limit);
    }

    public Offer getById(String id, boolean return_available_services) {
        String services = return_available_services ? "?return_available_services=true" : "";
        return super.getById(Offer.class, id + services).getData();
    }
}
