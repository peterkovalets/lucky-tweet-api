package org.peterkovalets.lucky_tweet_api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record RegisterRequest(
    @Pattern(regexp = "^[a-zA-Z0-9._-]+$",
            message = "can only contain Latin letters, digits, hyphens, and dots")
    @NotBlank @Size(min = 6, max = 30) String username,
    @NotBlank @Email String email,
    @NotBlank @Size(min = 8, max = 128) String password
) {
}
