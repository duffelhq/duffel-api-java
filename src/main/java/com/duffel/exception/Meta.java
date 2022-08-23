package com.duffel.exception;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;

/**
 * Metadata relating to the error, including the request ID for tracing.
 */
@Getter
@ToString
public class Meta {

    /**
     * The Duffel request ID, used to trace an interaction. Providing this in the event of any issues will assist in debugging.
     */
    @JsonProperty("request_id")
    private String requestId;

    /**
     * The status code of the response
     */
    @JsonProperty("status")
    private String status;

}
