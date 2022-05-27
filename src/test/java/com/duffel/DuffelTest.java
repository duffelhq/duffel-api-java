
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
import org.mockserver.client.MockServerClient;
import org.mockserver.junit.jupiter.MockServerExtension;
import org.mockserver.model.Parameter;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;

@ExtendWith(MockServerExtension.class)
class DuffelTest {
    private static final Logger LOG = LogManager.getLogger(DuffelTest.class);

    @Test
    void aircraft(MockServerClient mockClient) {
        mockClient.when(request().withMethod("GET").withPath("/air/aircraft"))
                .respond(response().withStatusCode(200).withBody(FixtureHelper.readFixture(this.getClass(), "/fixtures/aircraft.json")));
        mockClient.when(request().withMethod("GET").withPath("/air/aircraft/arc_00009oBdrPis4D1mAnkllK"))
                .respond(response().withStatusCode(200).withBody(FixtureHelper.readFixture(this.getClass(), "/fixtures/aircraft_by_id.json")));

        DuffelApiClient client = new DuffelApiClient("testKey", "http://localhost:" + mockClient.getPort());

        AircraftCollection aircraft = client.aircraftService.getPage();
        aircraft = client.aircraftService.getPage(null, aircraft.getAfter(), null);
        LOG.info(aircraft.getData().get(0));

        LOG.info(client.aircraftService.getById("arc_00009oBdrPis4D1mAnkllK"));
    }

    @Test
    void offerRequest(MockServerClient mockClient) {
        mockClient.when(request().withMethod("POST").withPath("/air/offer_requests"))
                .respond(response().withStatusCode(200).withBody(FixtureHelper.readFixture(this.getClass(), "/fixtures/offer_request.json")));
        mockClient.when(request().withMethod("GET").withPath("/air/offer_requests/orq_0000AJjvHnVrlmyXa6RqzY"))
                .respond(response().withStatusCode(200).withBody(FixtureHelper.readFixture(this.getClass(), "/fixtures/offer_request_by_id.json")));

        DuffelApiClient client = new DuffelApiClient("testKey", "http://localhost:" + mockClient.getPort());

        OfferRequest.Slice slice = new OfferRequest.Slice();
        slice.departureDate = "2022-05-26";
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
    void offer(MockServerClient mockClient) {
        mockClient.when(request().withMethod("GET").withPath("/air/offers/off_0000AJjwEHhflaMRlEm9NA"))
                .respond(response().withStatusCode(200).withBody(FixtureHelper.readFixture(this.getClass(), "/fixtures/offers_by_id_with_services.json")));
        mockClient.when(request().withMethod("GET").withPath("/air/offers")
                        .withQueryStringParameter(Parameter.param("limit", "50"))
                        .withQueryStringParameter(Parameter.param("offer_request_id", "orq_0000AJjwEHPaqntorAJiC5")))
                .respond(response().withStatusCode(200).withBody(FixtureHelper.readFixture(this.getClass(), "/fixtures/offers_by_offer_request_id.json")));

        DuffelApiClient client = new DuffelApiClient("testKey", "http://localhost:" + mockClient.getPort());

        String offerId = "off_0000AJjwEHhflaMRlEm9NA";
        client.offerService.getById(offerId, true);

        String offerRequestId = "orq_0000AJjwEHPaqntorAJiC5";
        OfferCollection offers = client.offerService.getPage(offerRequestId, null, null, null);
        assertEquals(1, offers.data.size());
    }
}
