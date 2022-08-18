package com.duffel.model.request;

import com.duffel.model.Data;
import com.duffel.model.response.orderchange.OrderChangeOffer;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@ToString
public class PendingOrderChange extends Data<OrderChangeOffer> {

    ///  <summary>
    ///  Duffel's unique identifier for the order change offer
    ///  </summary>
    @JsonProperty("selected_order_change_offer")
    private String selectedOrderChangeOfferId;

}
