package org.peterkovalets.lucky_tweet_api.dto;

import java.time.Instant;

public record PostResponse(
    Long id,
    String title,
    String content,
    String thumbnailUrl,
    String authorUsername,
    String authorAvatarUrl,
    boolean edited,
    Instant createdAt,
    Instant updatedAt
) {
}
