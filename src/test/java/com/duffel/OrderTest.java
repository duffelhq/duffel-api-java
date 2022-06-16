package com.duffel;

import com.duffel.model.OrderType;
import com.duffel.model.PassengerType;
import com.duffel.model.request.OrderPassenger;
import com.duffel.model.request.OrderRequest;
import com.duffel.model.request.OrderUpdate;
import com.duffel.model.response.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockserver.client.MockServerClient;
import org.mockserver.junit.jupiter.MockServerExtension;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;

@ExtendWith(MockServerExtension.class)
class OrderTest {

    @Test
    void post(MockServerClient mockClient) {
        mockClient.when(request().withMethod("POST").withPath("/air/offer_requests"))
                .respond(response().withStatusCode(200).withBody(FixtureHelper.readFixture(this.getClass(), "/fixtures/offer_request.json")));
        mockClient.when(request().withMethod("POST").withPath("/air/orders"))
                .respond(response().withStatusCode(200).withBody(FixtureHelper.readFixture(this.getClass(), "/fixtures/order.json")));

        DuffelApiClient client = new DuffelApiClient("testKey", "http://localhost:" + mockClient.getPort());

        OrderPassenger orderPassenger = new OrderPassenger();
        orderPassenger.setEmail("test@duffel.com");
        orderPassenger.setGivenName("Test");
        orderPassenger.setFamilyName("User");
        orderPassenger.setTitle("Mr");
        orderPassenger.setBornOn("1990-01-01");
        orderPassenger.setPassengerType(PassengerType.adult);
        orderPassenger.setId("pas_0000AJySzYPdjSE0eU1kxd");
        orderPassenger.setPhoneNumber("+447888888888");
        orderPassenger.setGender("m");

        OrderRequest orderRequest = new OrderRequest();
        orderRequest.setType(OrderType.hold);
        orderRequest.setSelectedOffers(List.of("off_0000AJySzfAebZOfc24O4e"));
        orderRequest.setPassengers(List.of(orderPassenger));

        Order order = client.orderService.post(orderRequest);

        assertEquals(1, order.getSlices().size());
        assertEquals(1, order.getPassengers().size());
        assertEquals(707.75d, order.getTotalAmount());
        assertEquals("Q2UABJ", order.getBookingReference());
    }

    @Test
    void getById(MockServerClient mockClient) {
        mockClient.when(request().withMethod("GET").withPath("/air/orders/ord_0000AKLlRADDc3YBx6X5c0"))
                .respond(response().withStatusCode(200).withBody(FixtureHelper.readFixture(this.getClass(), "/fixtures/order.json")));

        DuffelApiClient client = new DuffelApiClient("testKey", "http://localhost:" + mockClient.getPort());

        Order order = client.orderService.getById("ord_0000AKLlRADDc3YBx6X5c0");

        assertEquals(1, order.getSlices().size());
        assertEquals(1, order.getPassengers().size());
        assertEquals(707.75d, order.getTotalAmount());
        assertEquals("Q2UABJ", order.getBookingReference());
    }

    @Test
    void update(MockServerClient mockClient) {
        mockClient.when(request().withMethod("PATCH").withPath("/air/orders/ord_0000AKY0JiKODHllshwjaq"))
                .respond(response().withStatusCode(200)
                .withBody(FixtureHelper.readFixture(this.getClass(), "/fixtures/order_update.json")));

        DuffelApiClient client = new DuffelApiClient("testKey", "http://localhost:" + mockClient.getPort());


        OrderUpdate update = new OrderUpdate();
        update.setMetadata(Map.of("myKey", "myValue"));

        Order order = client.orderService.update("ord_0000AKY0JiKODHllshwjaq", update);

        assertEquals("ord_0000AKY0JiKODHllshwjaq", order.getId());
        assertEquals(Set.of("myKey"), order.getMetadata().keySet());
    }
}
