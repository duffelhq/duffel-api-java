package com.duffel.example;

import com.duffel.DuffelApiClient;
import com.duffel.model.*;
import com.duffel.model.request.OfferRequest;
import com.duffel.model.request.OrderRequest;
import com.duffel.model.request.Payment;
import com.duffel.model.request.ServiceRequest;
import com.duffel.model.response.*;
import com.duffel.model.response.order.metadata.CancelForAnyReasonMetadata;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static com.duffel.DuffelApiClient.DUFFEL_AIR_IATA;

public class BookWithCancelForAnyReasonIT {

    private static final Logger LOG = LogManager.getLogger();

    @Test
    void bookWithCancelForAnyReason() {
        String testApiKey = System.getenv("DUFFEL_ACCESS_TOKEN");

        DuffelApiClient client = new DuffelApiClient(testApiKey);
        LOG.info("🚀 Created a Duffel client");

        // Create an offer request
        OfferRequest.Slice slice = new OfferRequest.Slice();
        slice.setDepartureDate(LocalDate.now().plusDays(100).format(DateTimeFormatter.ISO_DATE));
        slice.setOrigin("LHR");
        slice.setDestination("FRA");

        Passenger passenger = new Passenger();
        passenger.setType(PassengerType.adult);
        passenger.setGivenName("Test");
        passenger.setFamilyName("Cancel");

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

        // Fetch the offer details, and request additional services
        offer = client.offerService.getById(offer.getId(), true);

        Service cfar = offer.getAvailableServices().stream().filter(s -> ServiceType.Type.cancel_for_any_reason == s.getServiceType()).findFirst().orElseThrow();
        LOG.info("🧨 Cancel For Any Reason service available with cost {}{}", cfar.getTotalCurrency(), cfar.getTotalAmount());
        LOG.info("🔐 T&Cs {}", ((CancelForAnyReasonMetadata) cfar.getMetadata()).getTermsAndConditionsUrl());
        LOG.info("📝 Copy {}", ((CancelForAnyReasonMetadata) cfar.getMetadata()).getMerchantCopy());

        BigDecimal newOrderTotalCost = offer.getTotalAmount().add(cfar.getTotalAmount());
        LOG.info("💳 Cost of flight offer plus CFAR is {}{}", offer.getTotalCurrency(), newOrderTotalCost);

        // Create an order
        OrderPassenger orderPassenger = new OrderPassenger();
        orderPassenger.setEmail("test@duffel.com");
        orderPassenger.setGivenName("Test");
        orderPassenger.setFamilyName("Cancel");
        orderPassenger.setTitle("Ms");
        orderPassenger.setBornOn("1990-01-01");
        orderPassenger.setPassengerType(PassengerType.adult);
        orderPassenger.setId(offer.getPassengers().get(0).getId());
        orderPassenger.setPhoneNumber("+447888888888");
        orderPassenger.setGender("m");

        Payment payment = new Payment();
        payment.setType(PaymentType.balance);
        // New order total is the offer total plus the cost of the cfar
        payment.setAmount(newOrderTotalCost);
        payment.setCurrency(offer.getTotalCurrency());

        ServiceRequest cfarService = new ServiceRequest();
        cfarService.setId(cfar.getId());
        cfarService.setQuantity(1);

        OrderRequest orderRequest = new OrderRequest();
        orderRequest.setType(OrderType.instant);
        orderRequest.setPayments(List.of(payment));
        orderRequest.setSelectedOffers(List.of(offer.getId()));
        orderRequest.setPassengers(List.of(orderPassenger));
        orderRequest.setServices(List.of(cfarService));

        Order order = client.orderService.post(orderRequest);
        LOG.info("🎉 Booked order {} under PNR {} successfully", order.getId(), order.getBookingReference());
    }

}
