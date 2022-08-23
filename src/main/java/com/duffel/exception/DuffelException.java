package com.duffel.exception;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

/**
 * Exception returned by Duffel, containing a list of errors that have occurred.
 */
@Getter
@ToString
public class DuffelException extends RuntimeException {

    /**
     * List of errors that caused the request to fail
     */
    @JsonProperty("errors")
    private List<Error> errors;

    /**
     * Metadata associated with the error
     */
    @JsonProperty("meta")
    private Meta meta;

}
