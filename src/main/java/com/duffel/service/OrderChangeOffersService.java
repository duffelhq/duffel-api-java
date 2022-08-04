package com.duffel.service;

import com.duffel.model.response.orderchange.OrderChangeOffer;
import com.duffel.model.response.orderchange.OrderChangeOfferCollection;
import com.duffel.net.ApiClient;
import lombok.AllArgsConstructor;
import lombok.Getter;

public class OrderChangeOffersService extends Resource<OrderChangeOffer, OrderChangeOfferCollection, OrderChangeOffer> {

    private static final String ENDPOINT = "/air/order_change_offers";

    public OrderChangeOffersService(ApiClient apiClient) {
        super(apiClient, ENDPOINT);
    }

    public OrderChangeOffer getById(String id) {
        return super.getById(OrderChangeOffer.class, id).getData();
    }

    public OrderChangeOfferCollection getPage(String before, String after, Integer limit, String orderChangeRequestId) {
        return super.getPage(OrderChangeOfferCollection.class, "&order_change_request_id=" + orderChangeRequestId, before, after, limit);
    }

    public OrderChangeOfferCollection getPage(String before, String after, Integer limit, String orderChangeRequestId, SortOptions sortOptions, Integer maxConnections) {
        String orderChangeRequestIdParam = "&order_change_request_id=" + orderChangeRequestId;
        String sortParam = (sortOptions == null) ? "" : sortOptions.getSortQueryString();
        String maxConnectionsParam = maxConnections == null ? "" : "&max_connections=" + maxConnections;

        String selectorParams = orderChangeRequestIdParam + sortParam + maxConnectionsParam;

        return super.getPage(OrderChangeOfferCollection.class, selectorParams, before, after, limit);
    }

    @Getter
    @AllArgsConstructor
    public static class SortOptions {
        private final OrderService.SortBy sortBy;
        private final OrderService.SortDirection direction;

        public String getSortQueryString() {
            return "&sort=" + ((direction == OrderService.SortDirection.descending) ? "-" : "") + sortBy;
        }
    }

    public enum SortBy {
        change_total_amount,
        total_duration
    }

    public enum SortDirection {
        ascending,
        descending
    }
}
