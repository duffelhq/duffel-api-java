package com.duffel.service;

import com.duffel.DuffelApiClient;
import com.duffel.model.AirlineInitiatedChange;
import com.duffel.model.AirlineInitiatedChangeActionTaken;
import com.duffel.model.AirlineInitiatedChangeCollection;
import com.duffel.model.AirlineInitiatedChangeUpdate;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockserver.client.MockServerClient;
import org.mockserver.junit.jupiter.MockServerExtension;
import org.mockserver.model.Parameter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;

@ExtendWith(MockServerExtension.class)
class AirlineInitiatedChangeTest {

    @Test
    void list(MockServerClient mockClient) {
        mockClient.when(request().withMethod("GET").withPath("/air/airline_initiated_changes")
                        .withQueryStringParameter(Parameter.param("order_id", "ord_0000AL963OkO78BJq62mPY")))
                .respond(response().withStatusCode(200).withBody(FixtureHelper.readFixture(this.getClass(), "/fixtures/airline_initiated_changes.json")));

        DuffelApiClient client = new DuffelApiClient("testKey", "http://localhost:" + mockClient.getPort());

        AirlineInitiatedChangeCollection airlineInitiatedChangeCollection = client.airlineInitiatedChangeService.list("ord_0000AL963OkO78BJq62mPY");

        assertNotNull(airlineInitiatedChangeCollection);
        assertEquals(6, airlineInitiatedChangeCollection.getData().size());
        assertEquals(1, airlineInitiatedChangeCollection.getData().get(0).getAdded().size());
        assertEquals("aic_0000AL965xLRXuSQ9vcGvo", airlineInitiatedChangeCollection.getData().get(0).getId());
        assertEquals(new BigDecimal("120.00"), airlineInitiatedChangeCollection.getData().get(0).getAdded().get(0).getConditions().get(0).getPenaltyAmount());
    }

    @Test
    void accept(MockServerClient mockClient) {
        mockClient.when(request().withMethod("POST").withPath("/air/airline_initiated_changes/aic_0000AMDmMaJsLqaDRvkIe8/actions/accept"))
                .respond(response().withStatusCode(200).withBody(FixtureHelper.readFixture(this.getClass(), "/fixtures/airline_initiated_change.json")));


        DuffelApiClient client = new DuffelApiClient("testKey", "http://localhost:" + mockClient.getPort());

        AirlineInitiatedChange airlineInitiatedChange = client.airlineInitiatedChangeService.accept("aic_0000AMDmMaJsLqaDRvkIe8");

        assertEquals("aic_0000AMDmMaJsLqaDRvkIe8", airlineInitiatedChange.getId());
        assertEquals(AirlineInitiatedChangeActionTaken.accepted, airlineInitiatedChange.getActionTaken());
    }

    @Test
    void update(MockServerClient mockClient) {
        mockClient.when(request().withMethod("PATCH").withPath("/air/airline_initiated_changes/aic_0000AMPr4lUGju0EEBrHCC"))
                .respond(response().withStatusCode(200).withBody(FixtureHelper.readFixture(this.getClass(), "/fixtures/airline_initiated_change_cancelled.json")));

        DuffelApiClient client = new DuffelApiClient("testKey", "http://localhost:" + mockClient.getPort());

        AirlineInitiatedChangeUpdate update = new AirlineInitiatedChangeUpdate();
        update.setActionTaken(AirlineInitiatedChangeActionTaken.cancelled);

        AirlineInitiatedChange airlineInitiatedChange = client.airlineInitiatedChangeService.update("aic_0000AMPr4lUGju0EEBrHCC", update);

        assertEquals("aic_0000AMPr4lUGju0EEBrHCC", airlineInitiatedChange.getId());
        assertEquals(AirlineInitiatedChangeActionTaken.cancelled, airlineInitiatedChange.getActionTaken());
        assertEquals(LocalDateTime.parse("2022-08-11T10:51:30.702393"), airlineInitiatedChange.getUpdatedAt());
    }

}
