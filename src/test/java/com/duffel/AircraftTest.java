package com.duffel;

import com.duffel.model.Aircraft;
import com.duffel.model.AircraftCollection;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockserver.client.MockServerClient;
import org.mockserver.junit.jupiter.MockServerExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;

@ExtendWith(MockServerExtension.class)
class AircraftTest {

    @Test
    void getById(MockServerClient mockClient) {
        mockClient.when(request().withMethod("GET").withPath("/air/aircraft/arc_00009oBdrPis4D1mAnkllK"))
                .respond(response().withStatusCode(200).withBody(FixtureHelper.readFixture(this.getClass(), "/fixtures/aircraft_by_id.json")));

        DuffelApiClient client = new DuffelApiClient("testKey", "http://localhost:" + mockClient.getPort());

        Aircraft aircraft = client.aircraftService.getById("arc_00009oBdrPis4D1mAnkllK");
        assertNotNull(aircraft);
        assertEquals("Airbus A220", aircraft.getName());
    }

    @Test
    void page(MockServerClient mockClient) {
        mockClient.when(request().withMethod("GET").withPath("/air/aircraft"))
                .respond(response().withStatusCode(200).withBody(FixtureHelper.readFixture(this.getClass(), "/fixtures/aircraft.json")));

        DuffelApiClient client = new DuffelApiClient("testKey", "http://localhost:" + mockClient.getPort());

        AircraftCollection aircraftCollection = client.aircraftService.getPage();
        assertNotNull(aircraftCollection);

        assertEquals(50, aircraftCollection.getData().size());
    }

}
