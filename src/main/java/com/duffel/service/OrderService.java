package com.duffel.service;

import com.duffel.model.OrderCollection;
import com.duffel.model.request.OrderRequest;
import com.duffel.model.request.OrderUpdate;
import com.duffel.model.request.PostData;
import com.duffel.model.response.Order;
import com.duffel.net.ApiClient;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class OrderService extends PostResource<Order, OrderCollection, Order> {

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
                                   SortOptions sortOptions, List<String> ownerId,
                                   List<String> originId, List<String> destinationId,
                                   DateTimeFilter dateTimeFilter,
                                   List<String> passengerName, Boolean requiresAirlineInitiatedChangeAction) {
        String bookingReferenceParam = (bookingReference == null || bookingReference.isEmpty()) ? "" : "&booking_reference=" + bookingReference;
        String awaitingPaymentParam = (awaitingPayment == null) ? "" : "&awaiting_payment=" + awaitingPayment;
        String sortParam = (sortOptions == null) ? "" : sortOptions.getSortQueryString();
        String ownerIdParam = (ownerId == null || ownerId.isEmpty()) ? "" : "&owner_id[]=" + String.join(",", ownerId);
        String originIdParam = (originId == null || originId.isEmpty()) ? "" : "&origin_id[]=" + String.join(",", originId);
        String destinationIdParam = (destinationId == null || destinationId.isEmpty()) ? "" : "&destination_id[]=" + String.join(",", destinationId);
        String dateTimeFilterParam = (dateTimeFilter == null) ? "" : dateTimeFilter.getDateTimeQueryString();
        String passengerNameParam = (passengerName == null || passengerName.isEmpty()) ? "" : "&passenger_name[]=" + String.join(",", passengerName);
        String requiresActionParam = (requiresAirlineInitiatedChangeAction == null) ? "" : "&requires_action=" + requiresAirlineInitiatedChangeAction;

        String selectorParams = bookingReferenceParam + awaitingPaymentParam + sortParam + ownerIdParam + originIdParam + destinationIdParam
            + dateTimeFilterParam + passengerNameParam + requiresActionParam;

        return super.getPage(OrderCollection.class, selectorParams, before, after, limit);
    }

    @Getter
    @AllArgsConstructor
    public static class DateTimeFilter {
        private AfterBeforeDateTime departingAt;
        private AfterBeforeDateTime arrivingAt;
        private AfterBeforeDateTime createdAt;

        public String getDateTimeQueryString() {
            String departingAtParam = (departingAt == null) ? "" : departingAt.getBeforeAfterQueryString( "departing_at");
            String arrivingAtParam = (arrivingAt == null) ? "" : arrivingAt.getBeforeAfterQueryString("arriving_at");
            String createdAtParam = (createdAt == null) ? "" : createdAt.getBeforeAfterQueryString("created_at");
            return departingAtParam + arrivingAtParam + createdAtParam;
        }
    }

    @Getter
    @AllArgsConstructor
    public static class AfterBeforeDateTime {
        private final LocalDateTime after;
        private final LocalDateTime before;

        public String getBeforeAfterQueryString(String field) {
            String afterParam = after == null ? "" : "&" + field + "[after]=" + after.format(DateTimeFormatter.ISO_DATE_TIME);
            String beforeParam = before == null ? "" : "&" + field + "[before]=" + before.format(DateTimeFormatter.ISO_DATE_TIME);
            return afterParam + beforeParam;
        }
    }

    @Getter
    @AllArgsConstructor
    public static class SortOptions {
        private final SortBy sortBy;
        private final SortDirection direction;

        public String getSortQueryString() {
            return "&sort=" + ((direction == SortDirection.descending) ? "-" : "") + sortBy;
        }
    }

    public enum SortBy {
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
