package com.duffel.example;

import com.duffel.DuffelApiClient;
import com.duffel.model.*;
import com.duffel.model.request.OfferRequest;
import com.duffel.model.request.OrderCancellationRequest;
import com.duffel.model.request.OrderRequest;
import com.duffel.model.request.Payment;
import com.duffel.model.response.Offer;
import com.duffel.model.response.OfferResponse;
import com.duffel.model.response.Order;
import com.duffel.model.response.OrderCancellation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static com.duffel.DuffelApiClient.DUFFEL_AIR_IATA;

public class BookAndChangeIT {

    private static final Logger LOG = LogManager.getLogger();

    @Test
    void bookAndChange() {
        String testApiKey = System.getenv("DUFFEL_ACCESS_TOKEN");
        testApiKey = "duffel_test_opbVnrL-pihnqp-SkYssn2xtPhVsjHzn21SA8uGzPAy";

        DuffelApiClient client = new DuffelApiClient(testApiKey);
        LOG.info("🚀 Created a Duffel client");

        // Create an offer request
        OfferRequest.Slice slice = new OfferRequest.Slice();
        slice.setDepartureDate(LocalDate.now().plusDays(14).format(DateTimeFormatter.ISO_DATE));
        slice.setOrigin("LHR");
        slice.setDestination("STR");

        Passenger passenger = new Passenger();
        passenger.setType(PassengerType.adult);
        passenger.setGivenName("Test");
        passenger.setFamilyName("User");

        OfferRequest request = new OfferRequest();
        request.setMaxConnections(0);
        request.setCabinClass(CabinClass.economy.name());
        request.setSlices(List.of(slice));
        request.setPassengers(List.of(passenger));

        OfferResponse offerResponse = client.offerRequestService.post(request);
        LOG.info("🕵️ Requested offers for origin destination pair {} to {}",
                slice.getOrigin(), slice.getDestination());

        // Select a Duffel Air offer
        Offer offer = offerResponse.getOffers().stream().filter((o) -> DUFFEL_AIR_IATA.equals(o.getOwner().getIataCode())).findFirst().orElseThrow();
        LOG.info("🧑‍💻 Selected offer to book {} on {}, costing {}{}",
                offer.getId(), offer.getOwner().getIataCode(), offer.getTotalCurrency(), offer.getTotalAmount());

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
        payment.setAmount(offer.getTotalAmount());
        payment.setCurrency(offer.getTotalCurrency());

        OrderRequest orderRequest = new OrderRequest();
        orderRequest.setType(OrderType.instant);
        orderRequest.setPayments(List.of(payment));
        orderRequest.setSelectedOffers(List.of(offer.getId()));
        orderRequest.setPassengers(List.of(orderPassenger));

        Order order = client.orderService.post(orderRequest);
        LOG.info("🎉 Booked order {} with PNR {} successfully", order.getId(), order.getBookingReference());

        // Do a search for changing this order
        // TODO
    }

}
