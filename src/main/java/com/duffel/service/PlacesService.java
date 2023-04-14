package com.duffel.service;

import com.duffel.model.Location;
import com.duffel.model.LocationCollection;
import com.duffel.net.ApiClient;

public class PlacesService extends Resource<Location, LocationCollection> {

    private static final String ENDPOINT = "/places/suggestions";

    public PlacesService(ApiClient apiClient) {
        super(apiClient, ENDPOINT);
    }

    public LocationCollection get(String query) {
        return super.get(LocationCollection.class, "?query=" + query);
    }

}
