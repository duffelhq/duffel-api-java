package com.duffel.exception;

import lombok.Getter;
import lombok.ToString;

/**
 * Exception related to the SDK, for example lack of configuration.
 */
@Getter
@ToString
public class SdkException extends RuntimeException {

    /**
     * Exception constructor.
     * @param message message back to the user
     */
    public SdkException(String message) {
        super(message);
    }
}
