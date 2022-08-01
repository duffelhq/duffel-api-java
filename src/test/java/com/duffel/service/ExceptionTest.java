package com.duffel.service;

import com.duffel.DuffelApiClient;
import com.duffel.exception.DuffelException;
import com.duffel.model.request.OfferRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockserver.client.MockServerClient;
import org.mockserver.junit.jupiter.MockServerExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;

@ExtendWith(MockServerExtension.class)
public class ExceptionTest {

    @Test
    void authorization_failure(MockServerClient mockClient) {
        mockClient.when(request().withMethod("POST"))
                .respond(response().withStatusCode(401)
                        .withBody(FixtureHelper.readFixture(this.getClass(), "/fixtures/exceptions/401_authorization.json")));

        DuffelApiClient client = new DuffelApiClient("testKey", "http://localhost:" + mockClient.getPort());

        DuffelException exception = assertThrows(DuffelException.class, () -> client.offerRequestService.post(new OfferRequest()));
        assertEquals( "401", exception.getMeta().getStatus());
        assertEquals("missing_authorization_header", exception.getErrors().get(0).getCode());

        mockClient.reset();
    }

    @Test
    void airline_internal(MockServerClient mockClient) {
        mockClient.when(request().withMethod("POST"))
                .respond(response().withStatusCode(500)
                        .withBody(FixtureHelper.readFixture(this.getClass(), "/fixtures/exceptions/500_airline_internal.json")));

        DuffelApiClient client = new DuffelApiClient("testKey", "http://localhost:" + mockClient.getPort());

        DuffelException exception = assertThrows(DuffelException.class, () -> client.offerRequestService.post(new OfferRequest()));
        assertEquals( "500", exception.getMeta().getStatus());

        mockClient.reset();
    }

}
