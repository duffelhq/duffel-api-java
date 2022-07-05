package com.duffel;

import com.duffel.model.response.SeatMap;
import org.apache.logging.log4j.LogManager;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockserver.client.MockServerClient;
import org.mockserver.junit.jupiter.MockServerExtension;
import org.mockserver.model.Parameter;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;

@ExtendWith(MockServerExtension.class)
class SeatMapsTest {

    @Test
    void getById(MockServerClient mockClient) {
        mockClient.when(request().withMethod("GET").withPath("/air/seat_maps/")
                .withQueryStringParameter(Parameter.param("offer_id", "off_0000ALBRPYohD349Rz4ryR")))
                .respond(response().withStatusCode(200).withBody(FixtureHelper.readFixture(this.getClass(), "/fixtures/seat_map.json")));

        DuffelApiClient client = new DuffelApiClient("testKey", "http://localhost:" + mockClient.getPort());

        List<SeatMap> seatMaps = client.seatMapsService.getById("off_0000ALBRPYohD349Rz4ryR");
        assertNotNull(seatMaps);
        LogManager.getLogger().info(seatMaps);
    }

}
