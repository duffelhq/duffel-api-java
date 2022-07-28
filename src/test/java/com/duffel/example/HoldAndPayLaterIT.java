package com.duffel.example;

import com.duffel.DuffelApiClient;
import com.duffel.model.*;
import com.duffel.model.request.OfferRequest;
import com.duffel.model.request.OrderRequest;
import com.duffel.model.request.Payment;
import com.duffel.model.request.PaymentRequest;
import com.duffel.model.response.Offer;
import com.duffel.model.response.OfferResponse;
import com.duffel.model.response.Order;
import com.duffel.model.response.PaymentResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static com.duffel.DuffelApiClient.DUFFEL_AIR_IATA;

public class HoldAndPayLaterIT {

    private static final Logger LOG = LogManager.getLogger();

    @Test
    void holdAndPayLater() {
        String testApiKey = System.getenv("DUFFEL_ACCESS_TOKEN");

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

        OrderRequest orderRequest = new OrderRequest();
        orderRequest.setType(OrderType.hold);
        orderRequest.setSelectedOffers(List.of(offer.getId()));
        orderRequest.setPassengers(List.of(orderPassenger));

        Order order = client.orderService.post(orderRequest);
        LOG.info("🎉 Booked hold order {} with PNR {} successfully", order.getId(), order.getBookingReference());

        // Later, fetch the order again
        order = client.orderService.getById(order.getId());
        LOG.info("💶 Fetched up to date price of {}{} for order {}",
                order.getTotalCurrency(), order.getTotalAmount(), order.getId());

        // Pay for the order
        Payment payment = new Payment();
        payment.setType(PaymentType.balance);
        payment.setAmount(order.getTotalAmount());
        payment.setCurrency(order.getTotalCurrency());

        PaymentRequest paymentRequest = new PaymentRequest();
        paymentRequest.setPayment(payment);
        paymentRequest.setOrderId(order.getId());
        PaymentResponse paymentResponse = client.paymentsService.create(paymentRequest);
        LOG.info("💶 Successfully paid {}{} for order {}",
                paymentResponse.getCurrency(), paymentResponse.getAmount(), order.getId());

        // Post payment, fetch the documents
        order = client.orderService.getById(order.getId());
        LOG.info("📃 Fetched {} documents for order {}",
                order.getDocuments().size(), order.getId());
    }

}
