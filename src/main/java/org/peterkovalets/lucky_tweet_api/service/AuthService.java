package org.peterkovalets.lucky_tweet_api.service;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.peterkovalets.lucky_tweet_api.dao.UserRepository;
import org.peterkovalets.lucky_tweet_api.dto.LoginRequest;
import org.peterkovalets.lucky_tweet_api.dto.RegisterRequest;
import org.peterkovalets.lucky_tweet_api.entity.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;

    @Value("${cookie.secure}")
    private boolean cookieSecure;

    @Value("${cookie.max-age}")
    private int cookieMaxAge;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder,
                       AuthenticationManager authenticationManager, JWTService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    public void register(RegisterRequest request, HttpServletResponse response) {
        userRepository.save(new User(
            request.username(),
            request.email(),
            passwordEncoder.encode(request.password())
        ));
        setAuthCookie(response, jwtService.generateToken(request.username()));
    }

    public void verify(LoginRequest request, HttpServletResponse response) {
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(request.username(), request.password())
        );
        setAuthCookie(response, jwtService.generateToken(request.username()));
    }

    private void setAuthCookie(HttpServletResponse response, String token) {
        Cookie cookie = new Cookie("token", token);
        cookie.setHttpOnly(true);
        cookie.setSecure(cookieSecure);
        cookie.setPath("/");
        cookie.setMaxAge(cookieMaxAge);
        cookie.setAttribute("SameSite", "Strict");
        response.addCookie(cookie);
    }
}
