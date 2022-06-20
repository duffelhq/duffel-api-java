package com.duffel.exception;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class SdkException extends RuntimeException {

    public SdkException(String message) {
        super(message);
    }
}
