package org.peterkovalets.lucky_tweet_api.dto;

import java.time.Instant;
import java.util.UUID;

public record PostResponse(
    UUID id,
    String title,
    String content,
    String thumbnailUrl,
    String authorUsername,
    Instant createdAt
) {
}
