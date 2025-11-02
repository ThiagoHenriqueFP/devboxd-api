package io.devboxd.boxd_api.application.post;

import io.devboxd.boxd_api.domain.post.Post;
import io.devboxd.boxd_api.domain.post.PostService;
import io.devboxd.boxd_api.infrastructure.CustomResponseEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PostControllerImpl implements PostController {
    private final PostService postService;

    public PostControllerImpl(PostService postService) {
        this.postService = postService;
    }

    @Override
    public ResponseEntity<CustomResponseEntity> getPostById(Long id) {
        Post post = this.postService.getPostById(id);

        return ResponseEntity.ok(new CustomResponseEntity(post.toDto()));
    }
}
