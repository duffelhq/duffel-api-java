package com.duffel.model.request;

import com.duffel.model.Data;
import com.duffel.model.OrderType;
import com.duffel.model.PaymentType;
import com.duffel.model.response.Order;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.Map;

@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@ToString
public class OrderUpdate extends Data<Order> {

    ///  <summary>
    ///  Metadata contains a set of key-value pairs that you can attach to an object. It can be useful for storing
    ///  additional information about the object, in a structured format. Duffel does not use this information.
    ///  You should not store sensitive information in this field.
    ///
    ///  The metadata is a collection of key-value pairs, both of which are strings. You can store a maximum of
    ///  50 key-value pairs, where each key has a maximum length of 40 characters and each value has a maximum length
    ///  of 500 characters.
    ///
    ///  Keys must only contain numbers, letters, dashes, or underscores.
    ///
    ///  To clear this field, set it to an empty object ({}).
    ///  </summary>
    @JsonProperty("metadata")
    private Map<String, String> metadata;

}
