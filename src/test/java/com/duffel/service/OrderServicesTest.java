package com.duffel.service;

import com.duffel.DuffelApiClient;
import com.duffel.model.PaymentType;
import com.duffel.model.request.OrderChangeServices;
import com.duffel.model.request.Payment;
import com.duffel.model.response.Order;
import com.duffel.model.response.OrderAvailableService;
import com.duffel.model.response.ServiceType;
import com.fasterxml.jackson.core.JsonProcessingException;
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
class OrderServicesTest {

    @Test
    void post(MockServerClient mockClient) {
        mockClient.when(request().withMethod("POST").withPath("/air/orders/ord_0000APEU0iPJJZHccjjxIJ/services"))
                .respond(response().withStatusCode(200).withBody(FixtureHelper.readFixture(this.getClass(), "/fixtures/order.json")));

        DuffelApiClient client = new DuffelApiClient("testKey", "http://localhost:" + mockClient.getPort());

        Payment payment = new Payment();
        payment.setType(PaymentType.balance);
        payment.setAmount(BigDecimal.valueOf(15.99));
        payment.setCurrency("GBP");

        com.duffel.model.request.Service service = new com.duffel.model.request.Service();
        service.setId("aso_0000APrcAFu2Wb53ufbvlK");
        service.setQuantity(1);

        OrderChangeServices orderChangeServices = new OrderChangeServices();
        orderChangeServices.setOrderId("ord_0000APEU0iPJJZHccjjxIJ");
        orderChangeServices.setPayment(payment);
        orderChangeServices.setAddServices(List.of(service));

        Order order = client.orderServicesService.post(orderChangeServices);

        assertEquals(2, order.getServices().size());
        assertEquals("ser_0000AL3E9lRtT7XMqrEHpD", order.getServices().get(0).getId());
    }

    @Test
    void get(MockServerClient mockClient) throws JsonProcessingException {
        mockClient.when(request().withMethod("GET").withPath("/air/orders/ord_0000AL3E9lRtT7XMqrEHp8/available_services"))
                .respond(response().withStatusCode(200).withBody(FixtureHelper.readFixture(this.getClass(), "/fixtures/order_available_services.json")));

        DuffelApiClient client = new DuffelApiClient("testKey", "http://localhost:" + mockClient.getPort());

        List<OrderAvailableService> services = client.orderServicesService.get("ord_0000AL3E9lRtT7XMqrEHp8");

        assertEquals(2, services.size());
        assertEquals(ServiceType.Type.baggage, services.get(0).getServiceType());
    }

}
