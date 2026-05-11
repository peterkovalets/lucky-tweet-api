package org.peterkovalets.lucky_tweet_api.service;

import org.peterkovalets.lucky_tweet_api.dao.PostRepository;
import org.peterkovalets.lucky_tweet_api.dto.CreatePostRequest;
import org.peterkovalets.lucky_tweet_api.dto.PostResponse;
import org.peterkovalets.lucky_tweet_api.entity.Post;
import org.peterkovalets.lucky_tweet_api.security.UserPrincipal;
import org.springframework.stereotype.Service;

@Service
public class PostService {

    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public PostResponse save(CreatePostRequest request, UserPrincipal principal) {
        Post post = postRepository.save(new Post(
            request.title(),
            request.content(),
            principal.getUser()
        ));
        return new PostResponse(
            post.getId(),
            post.getTitle(),
            post.getContent(),
            post.getThumbnailUrl(),
            principal.getUsername(),
            post.getCreatedAt()
        );
    }
}
