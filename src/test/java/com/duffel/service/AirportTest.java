package com.duffel.service;

import com.duffel.DuffelApiClient;
import com.duffel.model.Location;
import com.duffel.model.LocationCollection;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockserver.client.MockServerClient;
import org.mockserver.junit.jupiter.MockServerExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;

@ExtendWith(MockServerExtension.class)
class AirportTest {

    @Test
    void getById(MockServerClient mockClient) {
        mockClient.when(request().withMethod("GET").withPath("/air/airports/arp_str_de"))
                .respond(response().withStatusCode(200).withBody(FixtureHelper.readFixture(this.getClass(), "/fixtures/airport_by_id.json")));

        DuffelApiClient client = new DuffelApiClient("testKey", "http://localhost:" + mockClient.getPort());
        Location airport = client.airportService.getById("arp_str_de");
        assertNotNull(airport);
        assertEquals("Stuttgart", airport.getCityName());
        assertEquals("Europe/Berlin", airport.getTimeZone());
        assertEquals("arp_str_de", airport.getId());
    }

    @Test
    void page(MockServerClient mockClient) {
        mockClient.when(request().withMethod("GET").withPath("/air/airports"))
                .respond(response().withStatusCode(200).withBody(FixtureHelper.readFixture(this.getClass(), "/fixtures/airports.json")));

        DuffelApiClient client = new DuffelApiClient("testKey", "http://localhost:" + mockClient.getPort());
        LocationCollection airportCollection = client.airportService.getPage();
        assertNotNull(airportCollection);

        assertEquals(50, airportCollection.getData().size());
        assertEquals("ADL", airportCollection.getData().get(49).getIataCode());
    }

}
