package com.duffel.model.response.order.metadata;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;

/**
 * CFAR specifications.
 */
@EqualsAndHashCode
@Getter
@ToString
public class CancelForAnyReasonMetadata implements Metadata {

    /**
     * The amount the customer will be refunded if a valid CFAR refund is requested.
     */
    @JsonProperty("refund_amount")
    private BigDecimal refundAmount;

    /**
     * Link to Terms and Conditions
     */
    @JsonProperty("terms_and_conditions_url")
    private String termsAndConditionsUrl;

    /**
     * Text for merchants.
     */
    @JsonProperty("merchant_copy")
    private String merchantCopy;

}
