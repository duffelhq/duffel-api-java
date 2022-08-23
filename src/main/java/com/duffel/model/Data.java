package com.duffel.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * Wrapper around Duffel response models.
 * @param <T> response body type
 */
@EqualsAndHashCode
@Getter
@ToString
public abstract class Data<T> {

    /**
     * Data response.
     */
    @JsonProperty("data")
    private T data;

}
