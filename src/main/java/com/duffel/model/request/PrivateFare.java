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
public class PrivateFare {

    @JsonProperty("corporate_code")
    private String corporateCode;

    @JsonProperty("tracking_reference")
    private String trackingReference;

}
