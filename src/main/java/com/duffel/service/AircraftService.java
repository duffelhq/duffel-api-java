package com.duffel.service;

import com.duffel.model.Aircraft;
import com.duffel.model.AircraftCollection;
import com.duffel.net.ApiClient;

import java.util.List;

public class AircraftService extends Resource<Aircraft, AircraftCollection> {

    private static final String ENDPOINT = "/air/aircraft";

    public AircraftService(ApiClient apiClient) {
        super(apiClient, ENDPOINT);
    }

    public Aircraft getById(String id) {
        return super.getById(Aircraft.class, id).getData();
    }

    public AircraftCollection getPage() {
        return super.getPage(AircraftCollection.class, null, null, null);
    }

    public AircraftCollection getPage(Integer limit) {
        return super.getPage(AircraftCollection.class, null, null, limit);
    }

    public AircraftCollection getPage(String before, String after, Integer limit) {
        return super.getPage(AircraftCollection.class, before, after, limit);
    }

    public List<Aircraft> getAll() {
        return super.getAll(AircraftCollection.class);
    }
}
