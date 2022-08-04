package com.duffel.service;

import com.duffel.DuffelApiClient;
import com.duffel.model.CabinClass;
import com.duffel.model.Passenger;
import com.duffel.model.PassengerType;
import com.duffel.model.request.OfferRequest;
import com.duffel.model.response.OfferResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockserver.client.MockServerClient;
import org.mockserver.junit.jupiter.MockServerExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;

@ExtendWith(MockServerExtension.class)
class OfferRequestTest {

    @Test
    void post(MockServerClient mockClient) {
        mockClient.when(request().withMethod("POST").withPath("/air/offer_requests"))
                .respond(response().withStatusCode(200).withBody(FixtureHelper.readFixture(this.getClass(), "/fixtures/offer_request.json")));

        DuffelApiClient client = new DuffelApiClient("testKey", "http://localhost:" + mockClient.getPort());

        OfferRequest.Slice slice = new OfferRequest.Slice();
        slice.setDepartureDate("2022-06-15");
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

        assertNotNull(offerResponse);
        assertEquals(11, offerResponse.getOffers().size());
        assertEquals("economy", offerResponse.getCabinClass());
        assertFalse(offerResponse.isLiveMode());
    }

    @Test
    void getById(MockServerClient mockClient) {
        mockClient.when(request().withMethod("GET").withPath("/air/offer_requests/orq_0000AJyTUL2VxM0ciPFDRp"))
                .respond(response().withStatusCode(200).withBody(FixtureHelper.readFixture(this.getClass(), "/fixtures/offer_request_by_id.json")));

        DuffelApiClient client = new DuffelApiClient("testKey", "http://localhost:" + mockClient.getPort());

        OfferResponse offerResponse = client.offerRequestService.getById("orq_0000AJyTUL2VxM0ciPFDRp");

        assertNotNull(offerResponse);
        assertEquals(11, offerResponse.getOffers().size());
        assertEquals("economy", offerResponse.getCabinClass());
        assertFalse(offerResponse.isLiveMode());
    }
}
