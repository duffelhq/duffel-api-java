package com.duffel.service;

import com.duffel.DuffelApiClient;
import com.duffel.model.CabinClass;
import com.duffel.model.OrderChangeSlices;
import com.duffel.model.request.OrderChange;
import com.duffel.model.response.orderchange.OrderChangeOffer;
import com.duffel.model.response.orderchange.OrderChangeOfferCollection;
import com.duffel.model.response.orderchange.OrderChangeResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockserver.client.MockServerClient;
import org.mockserver.junit.jupiter.MockServerExtension;
import org.mockserver.model.Parameter;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;

@ExtendWith(MockServerExtension.class)
class OrderChangeOffersTest {

    @Test
    void getById(MockServerClient mockClient) {
        mockClient.when(request().withMethod("GET").withPath("/air/order_change_offers/oco_0000AM7U7HUJw3fr768XdC"))
                .respond(response().withStatusCode(200).withBody(FixtureHelper.readFixture(this.getClass(), "/fixtures/order_change_offer.json")));

        DuffelApiClient client = new DuffelApiClient("testKey", "http://localhost:" + mockClient.getPort());

        OrderChangeOffer offer = client.orderChangeOffersService.getById("oco_0000AM7U7HUJw3fr768XdC");

        assertEquals("oco_0000AM7U7HUJw3fr768XdC", offer.getId());
        assertEquals(new BigDecimal("216.40"), offer.getChangeTotalAmount());
    }

    @Test
    void page(MockServerClient mockClient) {
        mockClient.when(request().withMethod("GET").withPath("/air/order_change_offers")
                        .withQueryStringParameter(Parameter.param("limit", "50"))
                        .withQueryStringParameter("order_change_request_id", "ocr_0000AM7U7HTxxNOH5zyG4x"))
                .respond(response().withStatusCode(200).withBody(FixtureHelper.readFixture(this.getClass(), "/fixtures/order_change_offers.json")));

        DuffelApiClient client = new DuffelApiClient("testKey", "http://localhost:" + mockClient.getPort());

        OrderChangeOfferCollection offers = client.orderChangeOffersService.getPage(null, null, null, "ocr_0000AM7U7HTxxNOH5zyG4x");

        assertEquals(1, offers.getData().size());
        assertEquals("oco_0000AM7U7HUJw3fr768XdC", offers.getData().get(0).getId());
    }
}
