package com.duffel.service;


import com.duffel.model.Location;
import com.duffel.model.LocationCollection;
import com.duffel.net.ApiClient;

import java.util.List;

public class AirportService extends Resource<Location, LocationCollection, Location> {

    private static final String ENDPOINT = "/air/airports";

    public AirportService(ApiClient apiClient) {
        super(apiClient, ENDPOINT);
    }

    public Location getById(String id) {
        return super.getById(Location.class, id).getData();
    }

    public LocationCollection getPage() {
        return super.getPage(LocationCollection.class, null, null, null);
    }

    public LocationCollection getPage(Integer limit) {
        return super.getPage(LocationCollection.class, null, null, limit);
    }

    public LocationCollection getPage(String before, String after, Integer limit) {
        return super.getPage(LocationCollection.class, before, after, limit);
    }

    public List<Location> getAll() {
        return super.getAll(LocationCollection.class);
    }
}
