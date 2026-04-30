package org.peterkovalets.lucky_tweet_api.dao;

import org.peterkovalets.lucky_tweet_api.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
