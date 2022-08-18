import com.duffel.DuffelApiClient;
import com.duffel.model.Webhook;
import com.duffel.model.WebhookEvent;
import com.duffel.model.request.WebhookCreateRequest;
import com.duffel.model.request.WebhookUpdateRequest;
import com.duffel.model.response.SeatMap;
import com.duffel.model.response.seatmap.ElementType;
import com.duffel.model.response.seatmap.SeatService;
import com.duffel.model.response.seatmap.element.Element;
import com.duffel.model.response.seatmap.element.SeatElement;
import com.duffel.service.FixtureHelper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockserver.client.MockServerClient;
import org.mockserver.junit.jupiter.MockServerExtension;
import org.mockserver.model.Parameter;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;

@ExtendWith(MockServerExtension.class)
class WebhookTest {

    @Test
    void post(MockServerClient mockClient) {
        mockClient.when(request().withMethod("POST").withPath("/air/webhooks"))
                .respond(response().withStatusCode(200).withBody(FixtureHelper.readFixture(this.getClass(), "/fixtures/webhook.json")));

        DuffelApiClient client = new DuffelApiClient("testKey", "http://localhost:" + mockClient.getPort());

        WebhookCreateRequest webhookCreateRequest = new WebhookCreateRequest();
        webhookCreateRequest.setEvents(List.of(WebhookEvent.CREATED));
        webhookCreateRequest.setUrl("https://httpbin.org/post");

        Webhook webhook = client.webhookService.post(webhookCreateRequest);

        assertTrue(webhook.isActive());
        assertFalse(webhook.isLiveMode());
        assertEquals("https://test.com/webhook", webhook.getUrl());
        assertEquals("end_0000AMRu3m28KGtNP1o01Y", webhook.getId());
        assertEquals(WebhookEvent.CREATED, webhook.getEvents().stream().findFirst().orElseThrow());
    }

    @Test
    void patch(MockServerClient mockClient) {
        mockClient.when(request().withMethod("PATCH").withPath("/air/webhooks/end_0000AMRu3m28KGtNP1o01Y"))
                .respond(response().withStatusCode(200).withBody(FixtureHelper.readFixture(this.getClass(), "/fixtures/webhook.json")));

        DuffelApiClient client = new DuffelApiClient("testKey", "http://localhost:" + mockClient.getPort());

        WebhookUpdateRequest updateRequest = new WebhookUpdateRequest();
        updateRequest.setActive(true);
        updateRequest.setUrl("https://test.com/webhook");

        Webhook webhook = client.webhookService.patch("end_0000AMRu3m28KGtNP1o01Y", updateRequest);

        assertTrue(webhook.isActive());
        assertFalse(webhook.isLiveMode());
        assertEquals("https://test.com/webhook", webhook.getUrl());
        assertEquals("end_0000AMRu3m28KGtNP1o01Y", webhook.getId());
        assertEquals(WebhookEvent.CREATED, webhook.getEvents().stream().findFirst().orElseThrow());
    }

    @Test
    void ping(MockServerClient mockClient) {
        mockClient.when(request().withMethod("POST").withPath("/air/webhooks/end_0000AMRu3m28KGtNP1o01Y/actions/ping"))
                .respond(response().withStatusCode(204).withBody(FixtureHelper.readFixture(this.getClass(), "/fixtures/webhook_ping.json")));

        DuffelApiClient client = new DuffelApiClient("testKey", "http://localhost:" + mockClient.getPort());

        client.webhookService.ping("end_0000AMRu3m28KGtNP1o01Y");
    }

}
