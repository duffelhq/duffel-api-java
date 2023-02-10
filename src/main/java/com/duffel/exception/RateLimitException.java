package com.duffel.exception;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * HTTP 429 rate limited
 */
@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@ToString(callSuper = true)
public class RateLimitException extends DuffelException {

    /**
     * When will the rate limit reset again.
     */
    private LocalDateTime rateLimitReset;

}
