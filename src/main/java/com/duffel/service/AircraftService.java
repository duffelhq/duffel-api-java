package com.duffel.service;

import com.duffel.model.Aircraft;
import com.duffel.model.AircraftCollection;
import com.duffel.net.HttpClient;

import java.net.URISyntaxException;

public class AircraftService extends Resource<Aircraft, AircraftCollection> {

    private static final String ENDPOINT = "/aircraft";

    public AircraftService(HttpClient httpClient) {
        super(httpClient, ENDPOINT);
    }

    public AircraftCollection get() throws URISyntaxException {
        return super.get(AircraftCollection.class);
    }

    public Aircraft getById(String id) throws URISyntaxException {
        return super.getById(Aircraft.class, id).getData();
    }

    public AircraftCollection page(String before, String after, Integer limit) throws URISyntaxException {
        return super.page(AircraftCollection.class, before, after, limit);
    }
}
