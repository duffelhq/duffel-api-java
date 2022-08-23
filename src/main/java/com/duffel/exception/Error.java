package com.duffel.exception;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * Error information.
 */
@EqualsAndHashCode
@Getter
@ToString
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "type",
        defaultImpl = StandardError.class,
        visible = true
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = ValidationError.class, name = "validation_error")
})
public abstract class Error {

    /**
     * A machine-readable identifier for this specific error
     */
    @JsonProperty("code")
    private String code;

    /**
     * A URL pointing to a place in our documentation where you can read about the error
     */
    @JsonProperty("documentation_url")
    private String documentationUrl;

    /**
     * A more detailed human-readable description of what went wrong
     */
    @JsonProperty("message")
    private String message;

    /**
     * A quick and simple description of what went wrong
     */
    @JsonProperty("title")
    private String title;

    /**
     * A machine-readable identifier for the general category of error
     */
    @JsonProperty("type")
    private String errorType;

}
