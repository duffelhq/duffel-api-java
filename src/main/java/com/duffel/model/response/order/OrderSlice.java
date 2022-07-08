package com.duffel.model.response.order;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@Getter
@ToString
public class OrderSlice extends com.duffel.model.response.Slice {

    ///  <summary>
    ///  Whether this slice can be changed. This can only be true for paid orders.
    ///  </summary>
    @JsonProperty("changeable")
    private Boolean changeable;


}
