package com.duffel.exception;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * Exception with a string source.
 */
@EqualsAndHashCode(callSuper = true)
@Getter
@ToString
public class StandardError extends Error {

    @JsonProperty("source")
    private String source;

}
