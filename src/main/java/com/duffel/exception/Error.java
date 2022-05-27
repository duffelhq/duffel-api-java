package com.duffel.exception;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Error {

    ///  <summary>
    ///  A machine-readable identifier for this specific error
    ///  </summary>
    @JsonProperty("code")
    public String code;

    ///  <summary>
    ///  A URL pointing to a place in our documentation where you can read about the error
    ///  </summary>
    @JsonProperty("documentation_url")
    public String documentationUrl;

    ///  <summary>
    ///  A more detailed human-readable description of what went wrong
    ///  </summary>
    @JsonProperty("message")
    public String message;

    @JsonProperty("source")
    public ErrorSource source;

    ///  <summary>
    ///  A quick and simple description of what went wrong
    ///  </summary>
    @JsonProperty("title")
    public String title;

    ///  <summary>
    ///  A machine-readable identifier for the general category of error
    ///  </summary>
    @JsonProperty("type")
    public String errorType;

    @Override
    public String toString() {
        return "Error{" +
                "code='" + code + '\'' +
                ", documentationUrl='" + documentationUrl + '\'' +
                ", message='" + message + '\'' +
                ", source=" + source +
                ", title='" + title + '\'' +
                ", errorType='" + errorType + '\'' +
                '}';
    }

    public static class ErrorSource {

        @JsonProperty("field")
        public String field;

        @JsonProperty("pointer")
        public String pointer;

        @Override
        public String toString() {
            return "ErrorSource{" +
                    "field='" + field + '\'' +
                    ", pointer='" + pointer + '\'' +
                    '}';
        }
    }

}
