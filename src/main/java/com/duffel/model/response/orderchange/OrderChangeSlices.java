package com.duffel.model.response.orderchange;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@EqualsAndHashCode
@Getter
@ToString
public class OrderChangeSlices {

    ///  <summary>
    ///  The slices that will be added to the order
    ///  </summary>
    @JsonProperty("add")
    private List<OrderChangeSlice> add;

    ///  <summary>
    ///  The slices that will be removed from the order
    ///  </summary>
    @JsonProperty("remove")
    private List<OrderChangeSlice> remove;

}
