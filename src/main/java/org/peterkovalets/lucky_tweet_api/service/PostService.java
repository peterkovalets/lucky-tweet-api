package org.peterkovalets.lucky_tweet_api.service;

import org.peterkovalets.lucky_tweet_api.common.exceptions.PostNotFoundException;
import org.peterkovalets.lucky_tweet_api.dao.PostRepository;
import org.peterkovalets.lucky_tweet_api.dto.CreatePostRequest;
import org.peterkovalets.lucky_tweet_api.dto.PostResponse;
import org.peterkovalets.lucky_tweet_api.entity.Post;
import org.peterkovalets.lucky_tweet_api.security.UserPrincipal;
import org.springframework.stereotype.Service;

import java.util.List;

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
        return toResponse(post);
    }

    public List<PostResponse> findAll() {
        return postRepository.findAll().stream().map(this::toResponse).toList();
    }

    public PostResponse findById(Long id) {
        Post post = postRepository.findById(id)
            .orElseThrow(() -> new PostNotFoundException("Post not found"));
        return toResponse(post);
    }

    private PostResponse toResponse(Post post) {
        return new PostResponse(
            post.getId(),
            post.getTitle(),
            post.getContent(),
            post.getThumbnailUrl(),
            post.getUser().getUsername(),
            post.getUser().getAvatarUrl(),
            post.getCreatedAt(),
            post.getUpdatedAt()
        );
    }
}
