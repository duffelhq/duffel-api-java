package com.duffel.example;

import com.duffel.DuffelApiClient;
import com.duffel.model.*;
import com.duffel.model.request.OfferRequest;
import com.duffel.model.request.OrderRequest;
import com.duffel.model.request.Payment;
import com.duffel.model.response.Offer;
import com.duffel.model.response.OfferResponse;
import com.duffel.model.response.Order;
import com.duffel.model.response.SeatMap;
import com.duffel.model.response.seatmap.ElementType;
import com.duffel.model.response.seatmap.SeatService;
import com.duffel.model.response.seatmap.element.Element;
import com.duffel.model.response.seatmap.element.SeatElement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import static com.duffel.DuffelApiClient.DUFFEL_AIR_IATA;

public class BookWithSeatIT {

    private static final Logger LOG = LogManager.getLogger();

    @Test
    void bookWithSeat() {
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

        // Fetch seat maps for this offer
        List<SeatMap> seatMapList = client.seatMapsService.getById(offer.getId());
        // Let's book a seat on the first segment (there should only be one anyway for this short haul ZZ flight)
        SeatMap seatMap = seatMapList.get(0);
        // Let's just grab every seat element from the plane
        List<Element> seatElements = seatMap.getCabins().get(0).getRows().stream().flatMap(row -> row.getSections().stream().flatMap(section -> section.getElements().stream().filter((e) -> ElementType.seat == e.getType() && !((SeatElement) e).getAvailableServices().isEmpty()))).collect(Collectors.toList());
        // And choose a random seat
        SeatElement seat = (SeatElement) seatElements.stream().findAny().orElseThrow();
        SeatService seatService = seat.getAvailableServices().get(0);
        LOG.info("💺 Selecting random seat {} to book for segment {}", seat.getDesignator(), seatMap.getSegmentId());
        BigDecimal newOrderTotalCost = offer.getTotalAmount().add(seatService.getTotalAmount());
        LOG.info("💳 Cost of flight offer plus selected seat is {}{}", offer.getTotalCurrency(), newOrderTotalCost);

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
        // New order total is the offer total plus the cost of the seat
        payment.setAmount(newOrderTotalCost);
        payment.setCurrency(offer.getTotalCurrency());

        // We want to add this seat to the order
        OrderRequest.Service selectedService = new OrderRequest.Service();
        selectedService.setId(seatService.getId());
        selectedService.setQuantity(1);

        OrderRequest orderRequest = new OrderRequest();
        orderRequest.setType(OrderType.instant);
        orderRequest.setPayments(List.of(payment));
        orderRequest.setSelectedOffers(List.of(offer.getId()));
        orderRequest.setPassengers(List.of(orderPassenger));
        orderRequest.setServices(List.of(selectedService));

        Order order = client.orderService.post(orderRequest);
        LOG.info("🎉 Booked order {} with seat {} under PNR {} successfully",
                order.getId(), seat.getDesignator(), order.getBookingReference());
    }

}
