package com.duffel.exception;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

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

    ///  <summary>
    ///  A machine-readable identifier for this specific error
    ///  </summary>
    @JsonProperty("code")
    private String code;

    ///  <summary>
    ///  A URL pointing to a place in our documentation where you can read about the error
    ///  </summary>
    @JsonProperty("documentation_url")
    private String documentationUrl;

    ///  <summary>
    ///  A more detailed human-readable description of what went wrong
    ///  </summary>
    @JsonProperty("message")
    private String message;

    ///  <summary>
    ///  A quick and simple description of what went wrong
    ///  </summary>
    @JsonProperty("title")
    private String title;

    ///  <summary>
    ///  A machine-readable identifier for the general category of error
    ///  </summary>
    @JsonProperty("type")
    private String errorType;

}
