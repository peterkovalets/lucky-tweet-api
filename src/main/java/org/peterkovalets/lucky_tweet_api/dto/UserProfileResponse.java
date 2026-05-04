package org.peterkovalets.lucky_tweet_api.dto;

public record UserProfileResponse(
    String username,
    String email,
    String avatarUrl
) {
}
