package com.duffel.exception;

import com.duffel.model.response.seatmap.ElementType;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
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
//    @JsonTypeInfo(
//            use = JsonTypeInfo.Id.NAME,
//            property = "type",
//            defaultImpl = String.class,
//            visible = true
//    )
//    @JsonSubTypes({
//            @JsonSubTypes.Type(value = ValidationErrorSource.class, name = "validation_error")
//    })
    private String source;

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

//    public static class ErrorSource {
//
//        ///  <summary>
//        ///  The type of this element
//        ///  </summary>
//        @JsonProperty("type")
//        private ElementType type;
//
//    }
//
//    public static class StandardErrorSource extends ErrorSource {
//
//    }
//
//    public static class ValidationErrorSource extends ErrorSource {
//
//        @JsonProperty("field")
//        private String field;
//
//        @JsonProperty("pointer")
//        private String pointer;
//
//    }

}
