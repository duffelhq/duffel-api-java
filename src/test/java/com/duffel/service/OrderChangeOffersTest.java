package com.duffel.service;

import com.duffel.DuffelApiClient;
import com.duffel.model.CabinClass;
import com.duffel.model.OrderChangeSlices;
import com.duffel.model.request.OrderChange;
import com.duffel.model.response.orderchange.OrderChangeResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockserver.client.MockServerClient;
import org.mockserver.junit.jupiter.MockServerExtension;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;

@ExtendWith(MockServerExtension.class)
class OrderChangeOffersTest {

    @Test
    void getById(MockServerClient mockClient) {
        mockClient.when(request().withMethod("GET").withPath("/air/order_change_requests/ocr_0000AM52gchB3zeY8gF2Hp"))
                .respond(response().withStatusCode(200).withBody(FixtureHelper.readFixture(this.getClass(), "/fixtures/order_change_requests.json")));

        DuffelApiClient client = new DuffelApiClient("testKey", "http://localhost:" + mockClient.getPort());

        OrderChangeResponse orderChangeResponse = client.orderChangeRequestService.getById("ocr_0000AM52gchB3zeY8gF2Hp");

        assertEquals("ocr_0000AM52gchB3zeY8gF2Hp", orderChangeResponse.getId());
        assertEquals(6, orderChangeResponse.getOrderChangeOffers().size());
        assertEquals(new BigDecimal("81.60"), orderChangeResponse.getOrderChangeOffers().get(0).getChangeTotalAmount());
    }

    @Test
    void post(MockServerClient mockClient) {
        mockClient.when(request().withMethod("POST").withPath("/air/order_change_requests"))
                .respond(response().withStatusCode(200).withBody(FixtureHelper.readFixture(this.getClass(), "/fixtures/order_change_requests.json")));

        DuffelApiClient client = new DuffelApiClient("testKey", "http://localhost:" + mockClient.getPort());

        OrderChangeSlices.ChangeSlice addSlice = new OrderChangeSlices.ChangeSlice();
        addSlice.setOrigin("LHR");
        addSlice.setDestination("STR");
        addSlice.setCabinClass(CabinClass.economy);
        addSlice.setDepartureDate("2022-08-30");

        OrderChangeSlices.SliceId removeSlice = new OrderChangeSlices.SliceId();
        removeSlice.setSliceId("sli_0000AM52GZXHxmzu2l8Bj4");

        OrderChangeSlices orderChangeSlices = new OrderChangeSlices();
        orderChangeSlices.setAdd(List.of(addSlice));
        orderChangeSlices.setRemove(List.of(removeSlice));

        OrderChange orderChange = new OrderChange();
        orderChange.setOrderId("ord_0000AM52GZUS8KjFtxnvIz");
        orderChange.setSlices(orderChangeSlices);

        OrderChangeResponse orderChangeResponse = client.orderChangeRequestService.post(orderChange);

        assertEquals("ocr_0000AM52gchB3zeY8gF2Hp", orderChangeResponse.getId());
        assertEquals(6, orderChangeResponse.getOrderChangeOffers().size());
        assertEquals(new BigDecimal("81.60"), orderChangeResponse.getOrderChangeOffers().get(0).getChangeTotalAmount());
    }
}
