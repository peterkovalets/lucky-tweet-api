package org.peterkovalets.lucky_tweet_api.service;

import org.peterkovalets.lucky_tweet_api.dao.UserRepository;
import org.peterkovalets.lucky_tweet_api.entity.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void register(User user) {
        userRepository.save(new User(
            user.getUsername(),
            user.getEmail(),
            passwordEncoder.encode(user.getPassword())
        ));
    }
}
