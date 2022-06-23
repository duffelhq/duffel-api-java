

package com.duffel;

import com.duffel.model.OfferCollection;
import com.duffel.model.response.Offer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockserver.client.MockServerClient;
import org.mockserver.junit.jupiter.MockServerExtension;
import org.mockserver.model.Parameter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;

@ExtendWith(MockServerExtension.class)
class OfferTest {

    @Test
    void getById(MockServerClient mockClient) {
        mockClient.when(request().withMethod("GET").withPath("/air/offers/off_0000AJjwEHhflaMRlEm9NA"))
                .respond(response().withStatusCode(200).withBody(FixtureHelper.readFixture(this.getClass(), "/fixtures/offers_by_id_with_services.json")));

        DuffelApiClient client = new DuffelApiClient("testKey", "http://localhost:" + mockClient.getPort());

        Offer offer = client.offerService.getById("off_0000AJjwEHhflaMRlEm9NA", true);

        assertEquals(524.3, offer.getTotalAmount());
        assertEquals(1, offer.getSlices().size());
    }

    @Test
    void page(MockServerClient mockClient) {
        mockClient.when(request().withMethod("GET").withPath("/air/offers")
                        .withQueryStringParameter(Parameter.param("limit", "50"))
                        .withQueryStringParameter(Parameter.param("offer_request_id", "orq_0000AJjwEHPaqntorAJiC5")))
                .respond(response().withStatusCode(200).withBody(FixtureHelper.readFixture(this.getClass(), "/fixtures/offers_by_offer_request_id.json")));

        DuffelApiClient client = new DuffelApiClient("testKey", "http://localhost:" + mockClient.getPort());

        OfferCollection offers = client.offerService.getPage("orq_0000AJjwEHPaqntorAJiC5", null, null, null);

        assertEquals(1, offers.getData().size());
        assertEquals("off_0000AJjwEHhflaMRlEm9NA", offers.getData().get(0).getId());
    }
}
