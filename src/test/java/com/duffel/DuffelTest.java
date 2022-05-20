
package com.duffel;

import com.duffel.model.AircraftCollection;
import com.duffel.model.OfferCollection;
import com.duffel.model.Passenger;
import com.duffel.model.request.OfferRequest;
import com.duffel.model.response.OfferResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockserver.junit.jupiter.MockServerExtension;
import org.mockserver.client.MockServerClient;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockServerExtension.class)
class DuffelTest {
    private static final Logger LOG = LogManager.getLogger(DuffelTest.class);

    @Test
    void aircraft() throws URISyntaxException {
        DuffelApiClient client = new DuffelApiClient( "");

        AircraftCollection aircraft = client.aircraftService.get();
        aircraft = client.aircraftService.page(null, aircraft.getAfter(), null);
        LOG.info(aircraft.getData().get(0));

        LOG.info(client.aircraftService.getById("arc_00009oBdrPis4D1mAnkllK"));
    }

    @Test
    void offerRequest() throws URISyntaxException {
        DuffelApiClient client = new DuffelApiClient( "");

        OfferRequest.Slice slice = new OfferRequest.Slice();
        slice.departureDate = "2022-05-20";
        slice.origin = "LHR";
        slice.destination = "STR";
        List<OfferRequest.Slice> slices = new ArrayList<>();
        slices.add(slice);

        Passenger passenger = new Passenger();
        passenger.type = Passenger.PassengerType.adult;
        passenger.givenName = "Test";
        passenger.familyName = "Test";
        List<Passenger> passengers = new ArrayList<>();
        passengers.add(passenger);

        OfferRequest request = new OfferRequest();
        request.maxConnections = 0;
        request.cabinClass = "economy";
        request.slices = slices;
        request.passengers = passengers;

        OfferResponse response = client.offerRequestService.post(request);

        LOG.info(client.offerRequestService.getById(response.id));
    }

    @Test
    void offer() throws URISyntaxException {
        DuffelApiClient client = new DuffelApiClient( "");

        OfferRequest.Slice slice = new OfferRequest.Slice();
        slice.departureDate = "2022-05-20";
        slice.origin = "LHR";
        slice.destination = "STR";
        List<OfferRequest.Slice> slices = new ArrayList<>();
        slices.add(slice);

        Passenger passenger = new Passenger();
        passenger.type = Passenger.PassengerType.adult;
        passenger.givenName = "Test";
        passenger.familyName = "Test";
        List<Passenger> passengers = new ArrayList<>();
        passengers.add(passenger);

        OfferRequest request = new OfferRequest();
        request.maxConnections = 0;
        request.cabinClass = "economy";
        request.slices = slices;
        request.passengers = passengers;

        OfferResponse response = client.offerRequestService.post(request);
        String offerId = response.offers.get(0).id;

        LOG.info(client.offerService.getById(offerId, true));

        OfferCollection offers = client.offerService.page(response.id, null, null, null);
        LOG.info("offers " + offers);
    }

}
