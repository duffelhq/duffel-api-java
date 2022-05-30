package com.duffel.exception;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Meta {

    ///  <summary>
    ///  The Duffel request ID, used to trace an interaction. Providing this in the event of any issues
    ///  will assist in debugging.
    ///  </summary>
    @JsonProperty("request_id")
    private String requestId;

    ///  <summary>
    ///  The status code of the response
    ///  </summary>
    @JsonProperty("status")
    private String status;

}
