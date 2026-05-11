package org.peterkovalets.lucky_tweet_api.rest;

import jakarta.validation.Valid;
import org.peterkovalets.lucky_tweet_api.dto.CreatePostRequest;
import org.peterkovalets.lucky_tweet_api.dto.PostResponse;
import org.peterkovalets.lucky_tweet_api.security.UserPrincipal;
import org.peterkovalets.lucky_tweet_api.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public ResponseEntity<PostResponse> createPost(@Valid @RequestBody CreatePostRequest request,
                                           @AuthenticationPrincipal UserPrincipal principal) {
        PostResponse response = postService.save(request, principal);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(response);
    }
}
