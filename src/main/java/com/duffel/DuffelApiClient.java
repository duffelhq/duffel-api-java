package com.duffel;

import com.duffel.net.HttpClient;
import com.duffel.service.AircraftService;
import com.duffel.service.OfferRequestService;
import com.duffel.service.OfferService;

public class DuffelApiClient {

    public static final String API_ENDPOINT_STAGING = "https://api.staging.duffel.com/";
    public static final String API_ENDPOINT_PRODUCTION = "https://api.duffel.com/";
    public static final String API_VERSION = "beta";
    public static final String USER_AGENT = "Duffel/beta duffel_api_java";
    public static final Integer DEFAULT_PAGE_LIMIT = 50;

    public final AircraftService aircraftService;
    public final OfferService offerService;
    public final OfferRequestService offerRequestService;

    public DuffelApiClient(String apiKey) {
        this(apiKey, API_ENDPOINT_STAGING);
    }

    public DuffelApiClient(String apiKey, boolean production) {
        this(apiKey, production ? API_ENDPOINT_PRODUCTION : API_ENDPOINT_STAGING);
    }

    public DuffelApiClient(String apiKey, String endpoint) {
        HttpClient httpClient = new HttpClient(apiKey, endpoint);

        aircraftService = new AircraftService(httpClient);
        offerService = new OfferService(httpClient);
        offerRequestService = new OfferRequestService(httpClient);
    }

}
