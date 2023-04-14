package com.duffel;

import com.duffel.exception.SdkException;
import com.duffel.net.ApiClient;
import com.duffel.service.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DuffelApiClient {

    private static final Logger LOG = LogManager.getLogger();

    public static final String DUFFEL_URL = "https://api.duffel.com";
    public static final String DUFFEL_TOKEN_URL = DUFFEL_URL + "/duffel/tokens";
    public static final String API_VERSION = "v1";
    public static final String USER_AGENT = "Duffel/v1 duffel_api_java";
    public static final Integer DEFAULT_PAGE_LIMIT = 50;
    public static final Integer DEFAULT_GET_ALL_PAGE_SIZE = 200;
    public static final String DUFFEL_AIR_IATA = "ZZ";

    private static final String NO_API_KEY_MESSAGE = "Failed to provide API key, please generate one at " + DUFFEL_TOKEN_URL;

    public final AircraftService aircraftService;
    public final AirlineService airlineService;
    public final AirlineInitiatedChangeService airlineInitiatedChangeService;
    public final AirportService airportService;
    public final OfferService offerService;
    public final OfferPassengerService offerPassengerService;
    public final OfferRequestService offerRequestService;
    public final OrderChangeService orderChangeService;
    public final OrderChangeOffersService orderChangeOffersService;
    public final OrderCancellationService orderCancellationService;
    public final OrderChangeRequestService orderChangeRequestService;
    public final OrderService orderService;
    public final OrderServicesService orderServicesService;
    public final PaymentsService paymentsService;
    public final SeatMapsService seatMapsService;
    public final WebhookService webhookService;

    public DuffelApiClient(String apiKey) {
        this(apiKey, DUFFEL_URL);
    }

    public DuffelApiClient(String apiKey, String endpoint) {
        if (apiKey == null || apiKey.isEmpty()) {
            LOG.error(NO_API_KEY_MESSAGE);
            throw new SdkException(NO_API_KEY_MESSAGE);
        } else if (apiKey.startsWith("duffel_test_") || apiKey.startsWith("test_duffel_")) {
            LOG.info("🚧 Using test API key");
        } else {
            LOG.info("⚡️ Using live API key");
        }

        ApiClient apiClient = new ApiClient(apiKey, endpoint);

        aircraftService = new AircraftService(apiClient);
        airlineService = new AirlineService(apiClient);
        airlineInitiatedChangeService = new AirlineInitiatedChangeService(apiClient);
        airportService = new AirportService(apiClient);
        offerService = new OfferService(apiClient);
        offerPassengerService = new OfferPassengerService(apiClient);
        offerRequestService = new OfferRequestService(apiClient);
        orderChangeService = new OrderChangeService(apiClient);
        orderChangeOffersService = new OrderChangeOffersService(apiClient);
        orderCancellationService = new OrderCancellationService(apiClient);
        orderChangeRequestService = new OrderChangeRequestService(apiClient);
        orderService = new OrderService(apiClient);
        orderServicesService = new OrderServicesService(apiClient);
        paymentsService = new PaymentsService(apiClient);
        seatMapsService = new SeatMapsService(apiClient);
        webhookService = new WebhookService(apiClient);
    }

}
