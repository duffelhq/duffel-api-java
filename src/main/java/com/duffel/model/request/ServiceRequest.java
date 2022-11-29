package com.duffel.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@EqualsAndHashCode
@Getter
@Setter
@ToString
public class ServiceRequest {
    /**
     * The id of the service from the offer's available_services that you want to book
     */
    @JsonProperty("id")
    private String id;

    /**
     * The quantity of the service to book. This will always be 1 for seat services.
     */
    @JsonProperty("quantity")
    private Integer quantity;
}
