package com.duffel.example;

import com.duffel.DuffelApiClient;
import com.duffel.model.*;
import com.duffel.model.request.OfferRequest;
import com.duffel.model.request.OrderRequest;
import com.duffel.model.request.Payment;
import com.duffel.model.request.ServiceRequest;
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

public class BookWithLoyaltyAndExtraBaggageIT {

    private static final Logger LOG = LogManager.getLogger();

    @Test
    void bookWithLoyaltyAndExtraBaggage() {
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
        request.setCabinClass(CabinClass.economy);
        request.setSlices(List.of(slice));
        request.setPassengers(List.of(passenger));

        OfferResponse offerResponse = client.offerRequestService.post(request);
        LOG.info("🕵️ Requested offers for origin destination pair {} to {}",
                slice.getOrigin(), slice.getDestination());

        // Select a Duffel Air offer
        Offer offer = offerResponse.getOffers().stream().filter((o) -> DUFFEL_AIR_IATA.equals(o.getOwner().getIataCode())).findFirst().orElseThrow();
        LOG.info("🧑‍💻 Selected offer to book {} on {}, costing {}{}",
                offer.getId(), offer.getOwner().getIataCode(), offer.getTotalCurrency(), offer.getTotalAmount());

        LoyaltyProgrammeAccount loyalty = new LoyaltyProgrammeAccount();
        loyalty.setAirlineIataCode("ZZ");
        loyalty.setAccountNumber("12345");

        OfferPassenger offerPassenger = new OfferPassenger();
        offerPassenger.setGivenName("Test");
        offerPassenger.setFamilyName("Family");
        offerPassenger.setLoyaltyProgrammeAccountList(List.of(loyalty));
        client.offerPassengerService.patch(offer.getId(), offer.getPassengers().get(0).getId(), offerPassenger);

        // Fetch the offer details, and request additional services
        offer = client.offerService.getById(offer.getId(), true);
        Service bagService = offer.getAvailableServices().stream().filter(s -> ServiceType.Type.baggage == s.getServiceType()).findAny().orElseThrow();
        LOG.info("🎒 Can purchase an additional {}kg bag for {}{}",
                ((BaggageMetadata) bagService.getMetadata()).getMaximumWeightKg(), bagService.getTotalCurrency(), bagService.getTotalAmount());
        BigDecimal newOrderTotalCost = offer.getTotalAmount().add(bagService.getTotalAmount());
        LOG.info("💳 Cost of flight offer plus selected baggage option is {}{}", offer.getTotalCurrency(), newOrderTotalCost);

        // Create an order
        OrderPassenger orderPassenger = new OrderPassenger();
        orderPassenger.setEmail("test@duffel.com");
        orderPassenger.setGivenName("Test");
        orderPassenger.setFamilyName("Family");
        orderPassenger.setTitle("Ms");
        orderPassenger.setBornOn("1990-01-01");
        orderPassenger.setPassengerType(PassengerType.adult);
        orderPassenger.setId(offer.getPassengers().get(0).getId());
        orderPassenger.setPhoneNumber("+447888888888");
        orderPassenger.setGender("m");

        Payment payment = new Payment();
        payment.setType(PaymentType.balance);
        // New order total is the offer total plus the cost of the bag
        payment.setAmount(newOrderTotalCost);
        payment.setCurrency(offer.getTotalCurrency());

        // We want to add this bag to the order
        ServiceRequest selectedService = new ServiceRequest();
        selectedService.setId(bagService.getId());
        selectedService.setQuantity(1);

        OrderRequest orderRequest = new OrderRequest();
        orderRequest.setType(OrderType.instant);
        orderRequest.setPayments(List.of(payment));
        orderRequest.setSelectedOffers(List.of(offer.getId()));
        orderRequest.setPassengers(List.of(orderPassenger));
        orderRequest.setServices(List.of(selectedService));

        Order order = client.orderService.post(orderRequest);
        LOG.info("🎉 Booked order {} with additional {}kg of baggage under PNR {} successfully",
                order.getId(), ((BaggageMetadata) bagService.getMetadata()).getMaximumWeightKg(), order.getBookingReference());
    }

}
