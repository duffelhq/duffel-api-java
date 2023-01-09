import com.duffel.DuffelApiClient;
import com.duffel.model.*;
import com.duffel.model.request.*;
import com.duffel.model.response.*;
import com.duffel.model.response.order.metadata.BaggageMetadata;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class BookAndPostBookBaggageIT {

    private static final Logger LOG = LogManager.getLogger();

    @Test
    void bookAndPostBookBaggage() {
        String testApiKey = System.getenv("DUFFEL_ACCESS_TOKEN");
        DuffelApiClient client = new DuffelApiClient(testApiKey);

        // Create an offer request
        OfferRequest.Slice slice = new OfferRequest.Slice();
        slice.setDepartureDate(LocalDate.now().plusDays(60).format(DateTimeFormatter.ISO_DATE));
        slice.setOrigin("LGW");
        slice.setDestination("CDG");

        Passenger passenger = new Passenger();
        passenger.setType(PassengerType.adult);
        passenger.setGivenName("Test");
        passenger.setFamilyName("User");

        OfferRequest request = new OfferRequest();
        request.setMaxConnections(0);
        request.setCabinClass(CabinClass.economy);
        request.setSlices(List.of(slice));
        request.setPassengers(List.of(passenger));

        OfferResponse offerResponse = client.offerRequestService.post(request);
        LOG.info("🕵️ Requested offers for origin destination pair {} to {}",
                slice.getOrigin(), slice.getDestination());

        // Select a Duffel Air offer
        Offer offer = offerResponse.getOffers().stream().filter((o) -> "U2".equals(o.getOwner().getIataCode())).findFirst().orElseThrow();
        LOG.info("🧑‍💻 Selected offer to book {} on {}, costing {}{}",
                offer.getId(), offer.getOwner().getIataCode(), offer.getTotalCurrency(), offer.getTotalAmount());

        // Fetch the offer details, no additional services at book time
        offer = client.offerService.getById(offer.getId(), false);

        // Create an order
        OrderPassenger orderPassenger = new OrderPassenger();
        orderPassenger.setEmail("test@duffel.com");
        orderPassenger.setGivenName("Test");
        orderPassenger.setFamilyName("User");
        orderPassenger.setTitle("Ms");
        orderPassenger.setBornOn("1990-01-01");
        orderPassenger.setPassengerType(PassengerType.adult);
        orderPassenger.setId(offer.getPassengers().get(0).getId());
        orderPassenger.setPhoneNumber("+447888888888");
        orderPassenger.setGender("m");

        Payment payment = new Payment();
        payment.setType(PaymentType.balance);
        // New order total is the offer total plus the cost of the cfar
        payment.setAmount(offer.getTotalAmount());
        payment.setCurrency(offer.getTotalCurrency());

        OrderRequest orderRequest = new OrderRequest();
        orderRequest.setType(OrderType.instant);
        orderRequest.setPayments(List.of(payment));
        orderRequest.setSelectedOffers(List.of(offer.getId()));
        orderRequest.setPassengers(List.of(orderPassenger));

        Order order = client.orderService.post(orderRequest);
        LOG.info("🎉 Booked order {} under PNR {} successfully", order.getId(), order.getBookingReference());

        // Fetch post book services
        List<Service> availableServices = client.orderServicesService.get(order.getId());
        Service bagService = availableServices.stream().filter(s -> ServiceType.Type.baggage == s.getServiceType()).findAny().orElseThrow();
        LOG.info("🎒 Can purchase an additional {}kg bag for {}{}",
                ((BaggageMetadata) bagService.getMetadata()).getMaximumWeightKg(), bagService.getTotalCurrency(), bagService.getTotalAmount());


        // Add a bag to the order
        payment = new Payment();
        payment.setAmount(bagService.getTotalAmount());
        payment.setCurrency(bagService.getTotalCurrency());
        payment.setType(PaymentType.balance);

        ServiceRequest serviceRequest = new ServiceRequest();
        serviceRequest.setId(bagService.getId());
        serviceRequest.setQuantity(1);

        OrderChangeServices orderChangeServices = new OrderChangeServices();
        orderChangeServices.setOrderId(order.getId());
        orderChangeServices.setPayment(payment);
        orderChangeServices.setAddServices(List.of(serviceRequest));
        client.orderServicesService.post(orderChangeServices);
        LOG.info("🎉 Added a baggage ancillary for {}kg to order {}", ((BaggageMetadata) bagService.getMetadata()).getMaximumWeightKg(), order.getBookingReference());
    }

}
