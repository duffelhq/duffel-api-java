package com.duffel;

import com.duffel.net.ApiClient;
import com.duffel.service.AircraftService;
import com.duffel.service.AirlineService;
import com.duffel.service.OfferRequestService;
import com.duffel.service.OfferService;

public class DuffelApiClient {

    public enum Environment {
        STAGING("https://api.staging.duffel.com/"),
        PRODUCTION("https://api.duffel.com/");

        private String endpoint;

        Environment(String endpoint) {
            this.endpoint = endpoint;
        }
    }

    public static final String API_VERSION = "beta";
    public static final String USER_AGENT = "Duffel/beta duffel_api_java";
    public static final Integer DEFAULT_PAGE_LIMIT = 50;

    public final AircraftService aircraftService;

    public final AirlineService airlineService;
    public final OfferService offerService;
    public final OfferRequestService offerRequestService;

    public DuffelApiClient(String apiKey) {
        this(apiKey, Environment.STAGING);
    }

    public DuffelApiClient(String apiKey, Environment environment) {
        this(apiKey, environment.endpoint);
    }

    public DuffelApiClient(String apiKey, String endpoint) {
        ApiClient apiClient = new ApiClient(apiKey, endpoint);

        aircraftService = new AircraftService(apiClient);
        airlineService = new AirlineService(apiClient);
        offerService = new OfferService(apiClient);
        offerRequestService = new OfferRequestService(apiClient);
    }

}
