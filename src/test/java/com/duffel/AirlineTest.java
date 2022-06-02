
package com.duffel;

import com.duffel.model.Airline;
import com.duffel.model.AirlineCollection;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockserver.client.MockServerClient;
import org.mockserver.junit.jupiter.MockServerExtension;
import org.mockserver.model.Parameter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;

@ExtendWith(MockServerExtension.class)
class AirlineTest {

    @Test
    void getById(MockServerClient mockClient) {
        mockClient.when(request().withMethod("GET").withPath("/air/airlines/arl_00009VME7DBKeMags5CliQ"))
                .respond(response().withStatusCode(200).withBody(FixtureHelper.readFixture(this.getClass(), "/fixtures/airline_by_id.json")));

        DuffelApiClient client = new DuffelApiClient("testKey", "http://localhost:" + mockClient.getPort());

        Airline airline = client.airlineService.getById("arl_00009VME7DBKeMags5CliQ");
        assertNotNull(airline);
        assertEquals("BA", airline.getIataCode());
    }

    @Test
    void page(MockServerClient mockClient) {
        mockClient.when(request().withMethod("GET")
                        .withPath("/air/airlines")
                        .withQueryStringParameter(Parameter.param("after", "g3QAAAACZAACaWRtAAAAGmFybF8wMDAwOVZNRTdEQktlTWFnczVDbGl2ZAAEbmFtZW0AAAAKQmx1ZSBXaW5ncw=="))
                        .withQueryStringParameter(Parameter.param("limit", "100")))
                .respond(response().withStatusCode(200).withBody(FixtureHelper.readFixture(this.getClass(), "/fixtures/airlines.json")));

        DuffelApiClient client = new DuffelApiClient("testKey", "http://localhost:" + mockClient.getPort());

        AirlineCollection airlineCollection = client.airlineService.getPage("", "g3QAAAACZAACaWRtAAAAGmFybF8wMDAwOVZNRTdEQktlTWFnczVDbGl2ZAAEbmFtZW0AAAAKQmx1ZSBXaW5ncw==", 100);
        assertNotNull(airlineCollection);

        assertEquals(100, airlineCollection.getData().size());
    }

}
