package com.duffel.service;

import com.duffel.model.PagedData;
import com.duffel.model.response.SeatMap;
import com.duffel.model.response.SeatMapList;
import com.duffel.net.ApiClient;

import java.util.List;

public class SeatMapsService extends Resource<SeatMapList, PagedData<SeatMapList>, SeatMapList> {

    private static final String ENDPOINT = "/air/seat_maps";

    public SeatMapsService(ApiClient apiClient) {
        super(apiClient, ENDPOINT);
    }

    public List<SeatMap> getById(String offerId) {
        return super.getById(SeatMapList.class, "", "?offer_id=" + offerId).getData();
    }

}
