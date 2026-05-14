package org.peterkovalets.lucky_tweet_api.rest;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.peterkovalets.lucky_tweet_api.dto.LoginRequest;
import org.peterkovalets.lucky_tweet_api.dto.RegisterRequest;
import org.peterkovalets.lucky_tweet_api.dto.UserProfileResponse;
import org.peterkovalets.lucky_tweet_api.security.UserPrincipal;
import org.peterkovalets.lucky_tweet_api.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<Void> register(@Valid @RequestBody RegisterRequest request,
                                         HttpServletResponse response) {
        authService.register(request, response);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/login")
    public ResponseEntity<Void> login(@Valid @RequestBody LoginRequest request,
                                      HttpServletResponse response) {
        authService.verify(request, response);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpServletResponse response) {
        authService.logout(response);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/profile")
    public ResponseEntity<UserProfileResponse> getUserProfile(
        @AuthenticationPrincipal UserPrincipal principal
    ) {
        return ResponseEntity.ok(new UserProfileResponse(
            principal.getUsername(),
            principal.getEmail(),
            principal.getAvatarUrl()
        ));
    }
}
