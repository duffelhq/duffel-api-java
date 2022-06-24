package com.duffel.service;

import com.duffel.model.OrderCollection;
import com.duffel.model.request.OrderRequest;
import com.duffel.model.request.OrderUpdate;
import com.duffel.model.request.PostData;
import com.duffel.model.response.Order;
import com.duffel.net.ApiClient;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class OrderService extends PostResource<Order, OrderCollection> {

    private static final String ENDPOINT = "/air/orders";

    public OrderService(ApiClient apiClient) {
        super(apiClient, ENDPOINT);
    }

    public Order post(OrderRequest request) {
        return super.post(Order.class, new PostData<>(request)).getData();
    }

    public Order getById(String id) {
        return super.getById(Order.class, id).getData();
    }

    public Order update(String id, OrderUpdate update) {
        return super.update(Order.class, id, new PostData<>(update)).getData();
    }

    public OrderCollection getPage(String before, String after, Integer limit) {
        return super.getPage(OrderCollection.class, "", before, after, limit);
    }

    public OrderCollection getPage(String before, String after, Integer limit, String bookingReference, Boolean awaitingPayment,
                                   SortOptions sort, SortDirection sortDirection, List<String> ownerId,
                                   List<String> originId, List<String> destinationId,
                                   DateTimeFilter departingAt, DateTimeFilter arrivingAt, DateTimeFilter createdAt,
                                   List<String> passengerName, Boolean requiresAirlineInitiatedChangeAction) {
        String bookingReferenceParam = (bookingReference == null || bookingReference.isEmpty()) ? "" : "&booking_reference=" + bookingReference;
        String awaitingPaymentParam = (awaitingPayment == null) ? "" : "&awaiting_payment=" + awaitingPayment;
        String sortParam = (sort == null) ? "" : "&sort=" + ((sortDirection == SortDirection.descending) ? "-" : "") + sort;
        String ownerIdParam = (ownerId == null || ownerId.isEmpty()) ? "" : "&owner_id[]=" + String.join(",", ownerId);
        String originIdParam = (originId == null || originId.isEmpty()) ? "" : "&origin_id[]=" + String.join(",", originId);
        String destinationIdParam = (originId == null || destinationId.isEmpty()) ? "" : "&destination_id[]=" + String.join(",", destinationId);
        String departingAtParam = (departingAt == null) ? "" : "&departing_at" + departingAt.getParamString();
        String arrivingAtParam = (arrivingAt == null) ? "" : "&arriving_at" + arrivingAt.getParamString();
        String createdAtParam = (createdAt == null) ? "" : "&created_at" + createdAt.getParamString();
        String passengerNameParam = (passengerName == null || passengerName.isEmpty()) ? "" : "&passenger_name[]=" + String.join(",", passengerName);
        String requiresActionParam = (requiresAirlineInitiatedChangeAction == null) ? "" : "&requires_action=" + requiresAirlineInitiatedChangeAction;

        String selectorParams = bookingReferenceParam + awaitingPaymentParam + sortParam + ownerIdParam + originIdParam + destinationIdParam
            + departingAtParam + arrivingAtParam + createdAtParam + passengerNameParam + requiresActionParam;

        return super.getPage(OrderCollection.class, selectorParams, before, after, limit);
    }

    public static class DateTimeFilter {

        private final LocalDateTime time;
        private final BeforeAfter beforeAfter;

        public DateTimeFilter(LocalDateTime time, BeforeAfter beforeAfter) {
            this.time = time;
            this.beforeAfter = beforeAfter;
        }

        public String getParamString() {
            return "[" + beforeAfter + "]=" + time.format(DateTimeFormatter.ISO_DATE_TIME);
        }
    }

    public enum BeforeAfter {

        before,
        after
    }

    public enum SortOptions {
        payment_required_by,
        total_amount,
        created_at,
        next_departure
    }

    public enum SortDirection {
        ascending,
        descending
    }

}
