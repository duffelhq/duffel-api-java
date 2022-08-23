package com.duffel.exception;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * Validation exceptions have detailed field information to point to the problem area.
 */
@EqualsAndHashCode(callSuper = true)
@Getter
@ToString
public class ValidationError extends Error {

    @JsonProperty("source")
    private ValidationErrorSource source;

    @EqualsAndHashCode()
    @Getter
    @ToString
    public static class ValidationErrorSource {

        @JsonProperty("field")
        private String field;

        @JsonProperty("pointer")
        private String pointer;

    }

}
