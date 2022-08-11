package com.duffel.model.response.orderchange;

import com.duffel.model.Data;
import com.duffel.model.OrderChangeSlices;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Getter
@ToString
public class OrderChangeResponse extends Data<OrderChangeResponse> {

    ///  <summary>
    ///  The ISO 8601 datetime at which the order change request was created
    ///  </summary>
    @JsonProperty("created_at")
    private LocalDateTime createdAt;

    ///  <summary>
    ///  The ID of your order change request
    ///  </summary>
    @JsonProperty("id")
    private String id;

    ///  <summary>
    ///  Whether the order was created in live mode. This field will be set to true if the order was created in live
    ///  mode, or false if it was created in test mode.
    ///  </summary>
    @JsonProperty("live_mode")
    private boolean liveMode;

    ///  <summary>
    ///  The list of the offers available to perform change on the order.
    ///  </summary>
    @JsonProperty("order_change_offers")
    private List<OrderChangeOffer> orderChangeOffers;

    ///  <summary>
    ///  The order ID that you want to change
    ///  </summary>
    @JsonProperty("order_id")
    private String orderId;

    ///  <summary>
    ///  The slices to be added and/or removed
    ///  </summary>
    @JsonProperty("slices")
    private OrderChangeSlices slices;

    ///  <summary>
    ///  The ISO 8601 datetime at which the order change request was last updated
    ///  </summary>
    @JsonProperty("updated_at")
    private LocalDateTime updatedAt;

}
