package com.duffel.service;

import com.duffel.DuffelApiClient;
import com.duffel.model.OrderCancellationCollection;
import com.duffel.model.RefundDestination;
import com.duffel.model.request.OrderCancellationRequest;
import com.duffel.model.response.OrderCancellation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockserver.client.MockServerClient;
import org.mockserver.junit.jupiter.MockServerExtension;
import org.mockserver.model.Parameter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;

@ExtendWith(MockServerExtension.class)
class OrderCancellationTest {

    @Test
    void post(MockServerClient mockClient) {
        mockClient.when(request().withMethod("POST").withPath("/air/order_cancellations"))
                .respond(response().withStatusCode(200).withBody(FixtureHelper.readFixture(this.getClass(), "/fixtures/order_cancellation.json")));

        DuffelApiClient client = new DuffelApiClient("testKey", "http://localhost:" + mockClient.getPort());

        OrderCancellationRequest orderCancellationRequest = new OrderCancellationRequest();
        orderCancellationRequest.setOrderId("ord_0000ALiAP5a6cdSZ7Z8Lev");

        OrderCancellation orderCancellation = client.orderCancellationService.post(orderCancellationRequest);

        assertEquals("ore_0000ALkeIxcuQPCDKjE1r7", orderCancellation.getId());
        assertEquals("ord_0000ALiAP5a6cdSZ7Z8Lev", orderCancellation.getOrderId());
        assertEquals(RefundDestination.awaiting_payment, orderCancellation.getRefundTo());
    }

    @Test
    void page(MockServerClient mockClient) {
        mockClient.when(request().withMethod("GET").withPath("/air/order_cancellations").withQueryStringParameter(Parameter.param("limit", "10")))
                .respond(response().withStatusCode(200).withBody(FixtureHelper.readFixture(this.getClass(), "/fixtures/order_cancellations.json")));

        DuffelApiClient client = new DuffelApiClient("testKey", "http://localhost:" + mockClient.getPort());

        OrderCancellationCollection orderCancellations = client.orderCancellationService.getPage(null, null, 10);

        assertEquals(10, orderCancellations.getData().size());
        assertEquals("ore_0000ALWNYTVBBDh1eBxw25", orderCancellations.getData().get(9).getId());
    }

    @Test
    void getById(MockServerClient mockClient) {
        mockClient.when(request().withMethod("GET").withPath("/air/order_cancellations/ore_0000ALkeIxcuQPCDKjE1r7"))
                .respond(response().withStatusCode(200).withBody(FixtureHelper.readFixture(this.getClass(), "/fixtures/order_cancellation.json")));

        DuffelApiClient client = new DuffelApiClient("testKey", "http://localhost:" + mockClient.getPort());

        OrderCancellation orderCancellation = client.orderCancellationService.getById("ore_0000ALkeIxcuQPCDKjE1r7");

        assertEquals("ore_0000ALkeIxcuQPCDKjE1r7", orderCancellation.getId());
        assertEquals("ord_0000ALiAP5a6cdSZ7Z8Lev", orderCancellation.getOrderId());
        assertEquals(RefundDestination.awaiting_payment, orderCancellation.getRefundTo());
    }

    @Test
    void confirm(MockServerClient mockClient) {
        mockClient.when(request().withMethod("POST").withPath("/air/order_cancellations/ore_0000ALkeIxcuQPCDKjE1r7/actions/confirm"))
                .respond(response().withStatusCode(200).withBody(FixtureHelper.readFixture(this.getClass(), "/fixtures/order_cancellation_confirmed.json")));

        DuffelApiClient client = new DuffelApiClient("testKey", "http://localhost:" + mockClient.getPort());

        OrderCancellation orderCancellation = client.orderCancellationService.confirm("ore_0000ALkeIxcuQPCDKjE1r7");

        assertEquals("ore_0000ALkeIxcuQPCDKjE1r7", orderCancellation.getId());
        assertEquals("ord_0000ALiAP5a6cdSZ7Z8Lev", orderCancellation.getOrderId());
        assertEquals(RefundDestination.awaiting_payment, orderCancellation.getRefundTo());
    }


}
