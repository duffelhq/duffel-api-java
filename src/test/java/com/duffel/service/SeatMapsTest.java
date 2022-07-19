package com.duffel.service;

import com.duffel.DuffelApiClient;
import com.duffel.model.response.SeatMap;
import com.duffel.model.response.seatmap.ElementType;
import com.duffel.model.response.seatmap.SeatService;
import com.duffel.model.response.seatmap.element.Element;
import com.duffel.model.response.seatmap.element.SeatElement;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockserver.client.MockServerClient;
import org.mockserver.junit.jupiter.MockServerExtension;
import org.mockserver.model.Parameter;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
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
        assertEquals(1, seatMaps.get(0).getCabins().size());
        assertEquals(17, seatMaps.get(0).getCabins().get(0).getRows().size());
        assertEquals(ElementType.exit_row, seatMaps.get(0).getCabins().get(0).getRows().get(2).getSections().get(0).getElements().get(0).getType());

        // Right-hand side of row 11, seats ABC
        List<Element> row11 = seatMaps.get(0).getCabins().get(0).getRows().get(4).getSections().get(0).getElements();
        row11.forEach((element) -> {
            assertEquals(ElementType.seat, element.getType());
            assertNotNull(((SeatElement) element).getElementName());
            // This row is full, and has no seats available
            assertEquals(1, ((SeatElement) element).getAvailableServices().size());
            assertTrue((((SeatElement) element).getDesignator().startsWith("11")));
            SeatService service = ((SeatElement) element).getAvailableServices().get(0);
            assertEquals("pas_0000ALBRPOLG9dZsxnEDhn", service.getPassengerId());
            assertNotNull(service.getId());
        });

        // Left-hand side of row 20, seats DEF
        List<Element> row20 = seatMaps.get(0).getCabins().get(0).getRows().get(10).getSections().get(1).getElements();
        row20.forEach((element) -> {
            assertEquals(ElementType.seat, element.getType());
            assertNotNull(((SeatElement) element).getElementName());
            // This row is full, and has no seats available
            assertEquals(0, ((SeatElement) element).getAvailableServices().size());
            assertTrue((((SeatElement) element).getDesignator().startsWith("19")));
        });
    }

}
