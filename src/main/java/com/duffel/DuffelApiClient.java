package com.duffel;

import com.duffel.net.HttpClient;
import com.duffel.service.AircraftService;
import com.duffel.service.OfferRequestService;
import com.duffel.service.OfferService;

public class DuffelApiClient {

    public static final String API_ENDPOINT = "https://api.duffel.com/air";
    public static final String API_VERSION = "beta";
    public static final String USER_AGENT = "Duffel/beta duffel_api_java";
    public static final Integer DEFAULT_PAGE_LIMIT = 50;

    private final HttpClient httpClient;

    public static String apiKey;

    public final AircraftService aircraftService;
    public final OfferService offerService;
    public final OfferRequestService offerRequestService;

    public DuffelApiClient(String apiKey) {
        DuffelApiClient.apiKey = apiKey;
        httpClient = new HttpClient();

        aircraftService = new AircraftService(httpClient);
        offerService = new OfferService(httpClient);
        offerRequestService = new OfferRequestService(httpClient);
    }
}
