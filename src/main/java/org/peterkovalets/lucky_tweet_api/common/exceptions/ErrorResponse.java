package org.peterkovalets.lucky_tweet_api.common.exceptions;

import java.time.Instant;

public record ErrorResponse(
    int status,
    String error,
    Instant timestamp
) {
    public static ErrorResponse of(int status, String error) {
        return new ErrorResponse(status, error, Instant.now());
    }
}
