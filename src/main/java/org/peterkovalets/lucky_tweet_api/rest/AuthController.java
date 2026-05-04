package org.peterkovalets.lucky_tweet_api.rest;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.peterkovalets.lucky_tweet_api.dto.LoginRequest;
import org.peterkovalets.lucky_tweet_api.dto.RegisterRequest;
import org.peterkovalets.lucky_tweet_api.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<Void> register(@Valid @RequestBody RegisterRequest request,
                                         HttpServletResponse response) {
        userService.register(request, response);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/login")
    public ResponseEntity<Void> login(@Valid @RequestBody LoginRequest request,
                                      HttpServletResponse response) {
        userService.verify(request, response);
        return ResponseEntity.ok().build();
    }
}
