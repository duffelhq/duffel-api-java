package com.duffel.example;

import com.duffel.DuffelApiClient;
import com.duffel.model.*;
import com.duffel.model.request.*;
import com.duffel.model.response.Offer;
import com.duffel.model.response.OfferResponse;
import com.duffel.model.response.Order;
import com.duffel.model.response.orderchange.OrderChangeOffer;
import com.duffel.model.response.orderchange.OrderChangeResponse;
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
        LOG.info("🎉 Booked order {} with PNR {} for date {} successfully",
                order.getId(), order.getBookingReference(), order.getSlices().get(0).getSegments().get(0).getDepartingAt());

        // Do a search for changing this order
        OrderChangeSlices.ChangeSlice addSlice = new OrderChangeSlices.ChangeSlice();
        addSlice.setCabinClass(CabinClass.economy);
        addSlice.setOrigin("LHR");
        addSlice.setDestination("STR");
        addSlice.setDepartureDate(LocalDate.now().plusDays(28).format(DateTimeFormatter.ISO_DATE));

        OrderChangeSlices.SliceId removeSlice = new OrderChangeSlices.SliceId();
        removeSlice.setSliceId(order.getSlices().get(0).getId());

        OrderChangeSlices orderChangeSlices = new OrderChangeSlices();
        orderChangeSlices.setAdd(List.of(addSlice));
        orderChangeSlices.setRemove(List.of(removeSlice));

        OrderChange orderChange = new OrderChange();
        orderChange.setOrderId(order.getId());
        orderChange.setSlices(orderChangeSlices);
        OrderChangeResponse orderChangeResponse = client.orderChangeRequestService.post(orderChange);
        LOG.info("🔜 Looking to change flight date, got {} offers for alternatives",
                orderChangeResponse.getOrderChangeOffers().size());

        List<OrderChangeOffer> orderChangeOffers = orderChangeResponse.getOrderChangeOffers();

        PendingOrderChange pendingOrderChange = new PendingOrderChange();
        pendingOrderChange.setSelectedOrderChangeOfferId(orderChangeOffers.get(0).getId());

        OrderChangeOffer orderChangeOffer = client.orderChangeService.post(pendingOrderChange);
        LOG.info("♻️ Going to replace slice with new flight for {}{}",
                orderChangeOffer.getChangeTotalCurrency(), orderChangeOffer.getChangeTotalAmount());

        payment = new Payment();
        payment.setAmount(orderChangeOffer.getChangeTotalAmount());
        payment.setCurrency(orderChangeOffer.getChangeTotalCurrency());
        payment.setType(PaymentType.balance);

        OrderChangePaymentRequest changePaymentRequest = new OrderChangePaymentRequest();
        changePaymentRequest.setPayment(payment);
        client.orderChangeService.confirm(orderChangeOffer.getId(), changePaymentRequest);

        order = client.orderService.getById(order.getId());
        LOG.info("✈️ New flight date of {} for order {}", order.getSlices().get(0).getSegments().get(0).getDepartingAt(), order.getId());
    }

}
