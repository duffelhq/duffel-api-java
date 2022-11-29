package com.duffel.example;

import com.duffel.DuffelApiClient;
import com.duffel.model.*;
import com.duffel.model.request.OfferRequest;
import com.duffel.model.request.OrderRequest;
import com.duffel.model.request.Payment;
import com.duffel.model.response.*;
import com.duffel.model.response.order.metadata.BaggageMetadata;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static com.duffel.DuffelApiClient.DUFFEL_AIR_IATA;

public class BookWithExtraBaggageIT {

    private static final Logger LOG = LogManager.getLogger();

    @Test
    void bookWithExtraBaggage() {
        String testApiKey = System.getenv("DUFFEL_ACCESS_TOKEN");
        testApiKey = "duffel_test_vNlYFzQ3XY3-NaaV7w-y_1DRMz2gi9pr4sUENwmOiHL";
        DuffelApiClient client = new DuffelApiClient(testApiKey, "https://localhost:4000");
        LOG.info("🚀 Created a Duffel client");

        // Create an offer request
        OfferRequest.Slice slice = new OfferRequest.Slice();
        slice.setDepartureDate(LocalDate.now().plusDays(14).format(DateTimeFormatter.ISO_DATE));
        slice.setOrigin("LAS");
        slice.setDestination("DTW");

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
        Offer offer = offerResponse.getOffers().stream().filter((o) -> "NK".equals(o.getOwner().getIataCode())).findFirst().orElseThrow();
        LOG.info("🧑‍💻 Selected offer to book {} on {}, costing {}{}",
                offer.getId(), offer.getOwner().getIataCode(), offer.getTotalCurrency(), offer.getTotalAmount());

        // Fetch the offer details, and request additional services
        offer = client.offerService.getById(offer.getId(), true);
        Service bagService = offer.getAvailableServices().stream().filter(s -> ServiceType.Type.baggage == s.getServiceType()).findAny().orElseThrow();
        LOG.info("🎒 Can purchase an additional {}kg bag for {}{}, service {}",
                ((BaggageMetadata) bagService.getMetadata()).getMaximumWeightKg(), bagService.getTotalCurrency(), bagService.getTotalAmount(), bagService.getId());
        BigDecimal newOrderTotalCost = offer.getTotalAmount().add(bagService.getTotalAmount());
        LOG.info("💳 Cost of flight offer plus selected baggage option is {}{}", offer.getTotalCurrency(), newOrderTotalCost);
        LOG.info("😵‍💫 Rerunning offer price and service list call");
        offer = client.offerService.getById(offer.getId(), true);
        LOG.info("🎒 New service list has bag id {}", offer.getAvailableServices().stream().filter(s -> ServiceType.Type.baggage == s.getServiceType()).findAny().orElseThrow().getId());

        LOG.info("😵‍💫😵‍💫 Rerunning offer price and service list call");
        offer = client.offerService.getById(offer.getId(), true);
        LOG.info("🎒 New service list has bag id {}", offer.getAvailableServices().stream().filter(s -> ServiceType.Type.baggage == s.getServiceType()).findAny().orElseThrow().getId());

        // Create an order
//        OrderPassenger orderPassenger = new OrderPassenger();
//        orderPassenger.setEmail("test@duffel.com");
//        orderPassenger.setGivenName("Test");
//        orderPassenger.setFamilyName("User");
//        orderPassenger.setTitle("Ms");
//        orderPassenger.setBornOn("1990-01-01");
//        orderPassenger.setPassengerType(PassengerType.adult);
//        orderPassenger.setId(offer.getPassengers().get(0).getId());
//        orderPassenger.setPhoneNumber("+447888888888");
//        orderPassenger.setGender("m");
//
//        Payment payment = new Payment();
//        payment.setType(PaymentType.balance);
//        // New order total is the offer total plus the cost of the seat
//        payment.setAmount(newOrderTotalCost);
//        payment.setCurrency(offer.getTotalCurrency());

        // We want to add this seat to the order
//        OrderRequest.Service selectedService = new OrderRequest.Service();
//        selectedService.setId(bagService.getId());
//        selectedService.setQuantity(1);

//        OrderRequest orderRequest = new OrderRequest();
//        orderRequest.setType(OrderType.instant);
//        orderRequest.setPayments(List.of(payment));
//        orderRequest.setSelectedOffers(List.of(offer.getId()));
//        orderRequest.setPassengers(List.of(orderPassenger));
//        orderRequest.setServices(List.of(selectedService));
//
//        Order order = client.orderService.post(orderRequest);
//        LOG.info("🎉 Booked order {} with additional {}kg of baggage under PNR {} successfully",
//                order.getId(), ((BaggageMetadata) bagService.getMetadata()).getMaximumWeightKg(), order.getBookingReference());
    }

}
