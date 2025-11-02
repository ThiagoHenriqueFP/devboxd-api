package io.devboxd.boxd_api.application.postAuthor;

import io.devboxd.boxd_api.application.post.dto.PostResponseDTO;
import io.devboxd.boxd_api.domain.post.Post;
import io.devboxd.boxd_api.domain.post.PostService;
import io.devboxd.boxd_api.infrastructure.CustomResponseEntity;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PostAuthorControllerImpl implements PostAuthorController {

    private final PostService postService;

    public PostAuthorControllerImpl(PostService postService) {
        this.postService = postService;
    }

    @Override
    public ResponseEntity<CustomResponseEntity> getPostsFromAuthor(Long authorId, Pageable pageable) {
        int page = pageable.getPageNumber();
        int size = pageable.getPageSize() == 0? 10: pageable.getPageSize();
        Sort sort = pageable.getSort();

        Pageable parsed = PageRequest.of(page, size, sort);

        List<Post> posts = postService.getAllPostByAuthorId(authorId, pageable);

        List<PostResponseDTO> dtos = posts.stream().map(Post::toDto).toList();
        return ResponseEntity.ok(new CustomResponseEntity(dtos));
    }
}
