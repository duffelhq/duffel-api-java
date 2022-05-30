package com.duffel.exception;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Error {

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

    @JsonProperty("source")
    private ErrorSource source;

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

    public static class ErrorSource {

        @JsonProperty("field")
        private String field;

        @JsonProperty("pointer")
        private String pointer;

    }

}
