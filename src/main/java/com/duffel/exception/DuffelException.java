package com.duffel.exception;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class DuffelException extends Exception {

    ///  <summary>
    ///  List of errors that caused the request to fail
    ///  </summary>
    @JsonProperty("errors")
    public List<Error> errors;

    ///  <summary>
    ///  Metadata associated with the error
    ///  </summary>
    @JsonProperty("meta")
    public Meta meta;

    @Override
    public String toString() {
        return "DuffelException{" +
                "errors=" + errors +
                ", meta=" + meta +
                '}';
    }
}
