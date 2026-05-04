package org.peterkovalets.lucky_tweet_api.service;

import org.jspecify.annotations.NullMarked;
import org.peterkovalets.lucky_tweet_api.dao.UserRepository;
import org.peterkovalets.lucky_tweet_api.entity.User;
import org.peterkovalets.lucky_tweet_api.security.UserPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@NullMarked
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException("User not found."));

        return new UserPrincipal(user);
    }
}
