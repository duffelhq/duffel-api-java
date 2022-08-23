package com.duffel.model.response.orderchange;

import com.duffel.model.Data;
import com.duffel.model.OrderChangeSlices;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Order change response.
 */
@EqualsAndHashCode(callSuper = true)
@Getter
@ToString
public class OrderChangeResponse extends Data<OrderChangeResponse> {

    /**
     * The ISO 8601 datetime at which the order change request was created
     */
    @JsonProperty("created_at")
    private LocalDateTime createdAt;

    /**
     * The ID of your order change request
     */
    @JsonProperty("id")
    private String id;

    /**
     * Whether the order was created in live mode. This field will be set to true if the order was created in live
     * mode, or false if it was created in test mode.
     */
    @JsonProperty("live_mode")
    private boolean liveMode;

    /**
     * The list of the offers available to perform change on the order.
     */
    @JsonProperty("order_change_offers")
    private List<OrderChangeOffer> orderChangeOffers;

    /**
     * The order ID that you want to change
     */
    @JsonProperty("order_id")
    private String orderId;

    /**
     * The slices to be added and/or removed
     */
    @JsonProperty("slices")
    private OrderChangeSlices slices;

    /**
     * The ISO 8601 datetime at which the order change request was last updated
     */
    @JsonProperty("updated_at")
    private LocalDateTime updatedAt;

}
