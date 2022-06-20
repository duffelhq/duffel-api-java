package com.duffel;

import com.duffel.exception.SdkException;
import com.duffel.net.ApiClient;
import com.duffel.service.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DuffelApiClient {

    private static final Logger LOG = LogManager.getLogger();

    public static final String DUFFEL_URL = "https://app.duffel.com";
    public static final String DUFFEL_TOKEN_URL = DUFFEL_URL + "/duffel/tokens";
    public static final String API_VERSION = "beta";
    public static final String USER_AGENT = "Duffel/beta duffel_api_java";
    public static final Integer DEFAULT_PAGE_LIMIT = 50;

    public final AircraftService aircraftService;

    public final AirlineService airlineService;
    public final OfferService offerService;
    public final OfferRequestService offerRequestService;
    public final OrderService orderService;

    public DuffelApiClient(String apiKey) {
        this(apiKey, DUFFEL_URL);
    }

    public DuffelApiClient(String apiKey, String endpoint) {
        if (apiKey == null || apiKey.isEmpty()) {
            LOG.error("Failed to provide API key, please generate one at " + DUFFEL_TOKEN_URL);
            throw new SdkException("Failed to provide API key, please generate one at " + DUFFEL_TOKEN_URL);
        }

        ApiClient apiClient = new ApiClient(apiKey, endpoint);

        aircraftService = new AircraftService(apiClient);
        airlineService = new AirlineService(apiClient);
        offerService = new OfferService(apiClient);
        offerRequestService = new OfferRequestService(apiClient);
        orderService = new OrderService(apiClient);
    }

}
