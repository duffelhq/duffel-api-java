package com.duffel.service;

import com.duffel.model.Aircraft;
import com.duffel.model.AircraftCollection;
import com.duffel.net.HttpClient;

import java.net.URISyntaxException;

public class AircraftService extends Resource<Aircraft, AircraftCollection> {

    private static final String ENDPOINT = "/air/aircraft";

    public AircraftService(HttpClient httpClient) {
        super(httpClient, ENDPOINT);
    }

    public AircraftCollection get() {
        return super.get(AircraftCollection.class);
    }

    public Aircraft getById(String id) {
        return super.getById(Aircraft.class, id).getData();
    }

    public AircraftCollection page(String before, String after, Integer limit) {
        return super.page(AircraftCollection.class, before, after, limit);
    }
}
