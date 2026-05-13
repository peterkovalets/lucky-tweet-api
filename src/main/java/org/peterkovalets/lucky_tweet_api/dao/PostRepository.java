package org.peterkovalets.lucky_tweet_api.dao;

import org.peterkovalets.lucky_tweet_api.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
}
