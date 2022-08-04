package com.duffel.service;

import com.duffel.DuffelApiClient;
import com.duffel.model.OrderCollection;
import com.duffel.model.OrderType;
import com.duffel.model.PassengerType;
import com.duffel.model.OrderPassenger;
import com.duffel.model.request.OrderRequest;
import com.duffel.model.request.OrderUpdate;
import com.duffel.model.response.Order;
import com.duffel.model.response.order.metadata.BaggageMetadata;
import com.duffel.model.response.order.metadata.SeatMetadata;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockserver.client.MockServerClient;
import org.mockserver.junit.jupiter.MockServerExtension;
import org.mockserver.model.Parameter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
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
        assertEquals(new BigDecimal("193.06"), order.getTotalAmount());
        assertEquals("IXK4SC", order.getBookingReference());
    }

    @Test
    void page(MockServerClient mockClient) {
        mockClient.when(request().withMethod("GET").withPath("/air/orders").withQueryStringParameter(Parameter.param("limit", "10")))
                .respond(response().withStatusCode(200).withBody(FixtureHelper.readFixture(this.getClass(), "/fixtures/order_page_unfiltered.json")));
        mockClient.when(request().withMethod("GET").withPath("/air/orders")
                        .withQueryStringParameter(Parameter.param("limit", "15"))
                        .withQueryStringParameter(Parameter.param("before", "g3QAAAACZAACaWRtAAAAGm9yZF8wMDAwQUtoQVpuaGZVek1NOG92U2EwZAALaW5zZXJ0ZWRfYXR0AAAADWQACl9fc3RydWN0X19kAA9FbGl4aXIuRGF0ZVRpbWVkAAhjYWxlbmRhcmQAE0VsaXhpci5DYWxlbmRhci5JU09kAANkYXlhFGQABGhvdXJhF2QAC21pY3Jvc2Vjb25kaAJiAAVttmEGZAAGbWludXRlYSNkAAVtb250aGEGZAAGc2Vjb25kYTZkAApzdGRfb2Zmc2V0YQBkAAl0aW1lX3pvbmVtAAAAB0V0Yy9VVENkAAp1dGNfb2Zmc2V0YQBkAAR5ZWFyYgAAB-ZkAAl6b25lX2FiYnJtAAAAA1VUQw=="))
                        .withQueryStringParameter(Parameter.param("awaiting_payment", "false"))
                        .withQueryStringParameter(Parameter.param("sort", "total_amount"))
                        .withQueryStringParameter(Parameter.param("departing_at[after]", "2022-01-01T10:00:00"))
                        .withQueryStringParameter(Parameter.param("passenger_name[]", "Steve"))
                        .withQueryStringParameter(Parameter.param("requires_action", "false"))
                ).respond(response().withStatusCode(200).withBody(FixtureHelper.readFixture(this.getClass(), "/fixtures/order_page_filtered.json")));

        DuffelApiClient client = new DuffelApiClient("testKey", "http://localhost:" + mockClient.getPort());

        OrderCollection orderCollection = client.orderService.getPage(null, null, 10);
        assertEquals(10, orderCollection.getData().size());
        assertEquals("ord_0000AKmtVaxcYsF63cKPDd", orderCollection.getData().get(0).getId());
        assertEquals("BCN", orderCollection.getData().get(0).getSlices().get(0).getOrigin().getIataCode());
        assertEquals("LYS", orderCollection.getData().get(0).getSlices().get(0).getDestination().getIataCode());

        OrderCollection filteredOrderCollection = client.orderService.getPage(
                "g3QAAAACZAACaWRtAAAAGm9yZF8wMDAwQUtoQVpuaGZVek1NOG92U2EwZAALaW5zZXJ0ZWRfYXR0AAAADWQACl9fc3RydWN0X19kAA9FbGl4aXIuRGF0ZVRpbWVkAAhjYWxlbmRhcmQAE0VsaXhpci5DYWxlbmRhci5JU09kAANkYXlhFGQABGhvdXJhF2QAC21pY3Jvc2Vjb25kaAJiAAVttmEGZAAGbWludXRlYSNkAAVtb250aGEGZAAGc2Vjb25kYTZkAApzdGRfb2Zmc2V0YQBkAAl0aW1lX3pvbmVtAAAAB0V0Yy9VVENkAAp1dGNfb2Zmc2V0YQBkAAR5ZWFyYgAAB-ZkAAl6b25lX2FiYnJtAAAAA1VUQw==",
                null, 15, null, false,
                new OrderService.SortOptions(OrderService.SortBy.total_amount, OrderService.SortDirection.ascending),
                null, null, null,
                new OrderService.DateTimeFilter(new OrderService.AfterBeforeDateTime(LocalDateTime.parse("2022-01-01T10:00:00"), LocalDateTime.parse("2022-01-01T12:00:00")), null, null),
                List.of("Steve"), false);
        assertEquals(2, filteredOrderCollection.getData().size());
        assertEquals("ord_0000AKmtVaxcYsF63cKPDd", filteredOrderCollection.getData().get(0).getId());
        assertEquals("BCN", filteredOrderCollection.getData().get(0).getSlices().get(0).getOrigin().getIataCode());
        assertEquals("LYS", filteredOrderCollection.getData().get(0).getSlices().get(0).getDestination().getIataCode());
    }

    @Test
    void getById(MockServerClient mockClient) {
        mockClient.when(request().withMethod("GET").withPath("/air/orders/ord_0000AL3E9lRtT7XMqrEHp8"))
                .respond(response().withStatusCode(200).withBody(FixtureHelper.readFixture(this.getClass(), "/fixtures/order.json")));

        DuffelApiClient client = new DuffelApiClient("testKey", "http://localhost:" + mockClient.getPort());

        Order order = client.orderService.getById("ord_0000AL3E9lRtT7XMqrEHp8");

        assertEquals(1, order.getSlices().size());
        assertEquals(1, order.getPassengers().size());
        assertEquals(new BigDecimal("193.06"), order.getTotalAmount());
        assertEquals("IXK4SC", order.getBookingReference());
        assertEquals(2, order.getServices().size());
        assertEquals(23, ((BaggageMetadata) order.getServices().get(0).getMetadata()).getMaximumWeightKg());
        assertEquals("32C", ((SeatMetadata) order.getServices().get(1).getMetadata()).getDesignator());
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
