package com.duffel.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Data for POST operation, wrapped in a `data` tag.
 * @param <T> post body type
 */
@EqualsAndHashCode
@Getter
@Setter
@ToString
public class PostData<T> {

    /**
     * Constructor
     * @param data data to post to Duffel
     */
    public PostData(T data) {
        this.data = data;
    }

    /**
     * Data to send to Duffel
     */
    @JsonProperty("data")
    private T data;

}
