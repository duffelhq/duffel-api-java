package com.duffel;

import com.duffel.model.PaymentType;
import com.duffel.model.request.Payment;
import com.duffel.model.request.PaymentRequest;
import com.duffel.model.response.PaymentResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockserver.client.MockServerClient;
import org.mockserver.junit.jupiter.MockServerExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;

@ExtendWith(MockServerExtension.class)
class PaymentsTest {

    @Test
    void create(MockServerClient mockClient) {
        mockClient.when(request().withMethod("POST").withPath("/air/payments"))
                .respond(response().withStatusCode(200).withBody(FixtureHelper.readFixture(this.getClass(), "/fixtures/payment.json")));

        DuffelApiClient client = new DuffelApiClient("testKey", "http://localhost:" + mockClient.getPort());

        Payment payment = new Payment();
        payment.setType(PaymentType.balance);
        payment.setAmount(new BigDecimal("458.92"));
        payment.setCurrency("GBP");

        PaymentRequest paymentRequest = new PaymentRequest();
        paymentRequest.setOrderId("ord_0000ALv2e9p8Z1Ac162Xz6");
        paymentRequest.setPayment(payment);

        PaymentResponse paymentResponse = client.paymentsService.create(paymentRequest);
        assertEquals("pay_0000ALv2y0HVLbnmjIx7Ng", paymentResponse.getId());
    }
}
