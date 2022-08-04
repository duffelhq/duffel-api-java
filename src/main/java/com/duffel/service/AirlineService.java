package com.duffel.service;

import com.duffel.model.Airline;
import com.duffel.model.AirlineCollection;
import com.duffel.net.ApiClient;

import java.util.List;

public class AirlineService extends Resource<Airline, AirlineCollection, Airline> {

    private static final String ENDPOINT = "/air/airlines";

    public AirlineService(ApiClient apiClient) {
        super(apiClient, ENDPOINT);
    }

    public Airline getById(String id) {
        return super.getById(Airline.class, id).getData();
    }

    public AirlineCollection getPage() {
        return super.getPage(AirlineCollection.class, null, null, null);
    }

    public AirlineCollection getPage(Integer limit) {
        return super.getPage(AirlineCollection.class, null, null, limit);
    }

    public AirlineCollection getPage(String before, String after, Integer limit) {
        return super.getPage(AirlineCollection.class, before, after, limit);
    }

    public List<Airline> getAll() {
        return super.getAll(AirlineCollection.class);
    }
}
