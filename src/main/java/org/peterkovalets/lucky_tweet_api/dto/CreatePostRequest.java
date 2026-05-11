package org.peterkovalets.lucky_tweet_api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreatePostRequest(
    @NotBlank @Size(min = 3, max = 100) String title,
    @NotBlank @Size(min = 1, max = 1000) String content
) {
}
