package com.duffel.exception;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Meta {

    ///  <summary>
    ///  The Duffel request ID, used to trace an interaction. Providing this in the event of any issues
    ///  will assist in debugging.
    ///  </summary>
    @JsonProperty("request_id")
    public String requestId;

    ///  <summary>
    ///  The status code of the response
    ///  </summary>
    @JsonProperty("status")
    public String status;

    @Override
    public String toString() {
        return "Meta{" +
                "requestId='" + requestId + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
