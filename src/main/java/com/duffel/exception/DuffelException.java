package com.duffel.exception;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
public class DuffelException extends RuntimeException {

    ///  <summary>
    ///  List of errors that caused the request to fail
    ///  </summary>
    @JsonProperty("errors")
    private List<Error> errors;

    ///  <summary>
    ///  Metadata associated with the error
    ///  </summary>
    @JsonProperty("meta")
    private Meta meta;

}
