package com.duffel.service;

import com.duffel.DuffelApiClient;
import com.duffel.model.PaymentType;
import com.duffel.model.request.OrderChangePaymentRequest;
import com.duffel.model.request.Payment;
import com.duffel.model.request.PendingOrderChange;
import com.duffel.model.response.orderchange.OrderChangeOffer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockserver.client.MockServerClient;
import org.mockserver.junit.jupiter.MockServerExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;

@ExtendWith(MockServerExtension.class)
class OrderChangeTest {

    @Test
    void getById(MockServerClient mockClient) {
        mockClient.when(request().withMethod("GET").withPath("/air/order_changes/oce_0000AM7QBMJYkHVH0iPXeK"))
                .respond(response().withStatusCode(200).withBody(FixtureHelper.readFixture(this.getClass(), "/fixtures/order_change.json")));

        DuffelApiClient client = new DuffelApiClient("testKey", "http://localhost:" + mockClient.getPort());

        OrderChangeOffer orderChangeOffer = client.orderChangeService.getById("oce_0000AM7QBMJYkHVH0iPXeK");

        assertEquals("oce_0000AM7QBMJYkHVH0iPXeK", orderChangeOffer.getId());
        assertEquals(new BigDecimal("50.00"), orderChangeOffer.getPenaltyTotalAmount());
        assertEquals(new BigDecimal("97.67"), orderChangeOffer.getNewTotalAmount());
    }

    @Test
    void post(MockServerClient mockClient) {
        mockClient.when(request().withMethod("POST").withPath("/air/order_changes"))
                .respond(response().withStatusCode(200).withBody(FixtureHelper.readFixture(this.getClass(), "/fixtures/order_change.json")));

        DuffelApiClient client = new DuffelApiClient("testKey", "http://localhost:" + mockClient.getPort());

        PendingOrderChange pendingOrderChange = new PendingOrderChange();
        pendingOrderChange.setSelectedOrderChangeOfferId("oco_0000AM7QARj78b4nVSve4G");

        OrderChangeOffer orderChangeOffer = client.orderChangeService.post(pendingOrderChange);
        assertEquals("oce_0000AM7QBMJYkHVH0iPXeK", orderChangeOffer.getId());
        assertEquals(new BigDecimal("50.00"), orderChangeOffer.getPenaltyTotalAmount());
        assertEquals(new BigDecimal("97.67"), orderChangeOffer.getNewTotalAmount());
    }

    @Test
    void confirm(MockServerClient mockClient) {
        mockClient.when(request().withMethod("POST").withPath("/air/order_changes/oce_0000AM7QBMJYkHVH0iPXeK/actions/confirm"))
                .respond(response().withStatusCode(200).withBody(FixtureHelper.readFixture(this.getClass(), "/fixtures/order_change.json")));

        DuffelApiClient client = new DuffelApiClient("testKey", "http://localhost:" + mockClient.getPort());

        Payment payment = new Payment();
        payment.setCurrency("GBP");
        payment.setAmount(new BigDecimal("76.00"));
        payment.setType(PaymentType.balance);
        OrderChangePaymentRequest paymentRequest = new OrderChangePaymentRequest();
        paymentRequest.setPayment(payment);

        OrderChangeOffer orderChangeOffer = client.orderChangeService.confirm("oce_0000AM7QBMJYkHVH0iPXeK", paymentRequest);
        assertEquals("oce_0000AM7QBMJYkHVH0iPXeK", orderChangeOffer.getId());
        assertEquals(new BigDecimal("50.00"), orderChangeOffer.getPenaltyTotalAmount());
        assertEquals(new BigDecimal("97.67"), orderChangeOffer.getNewTotalAmount());
    }
}
