package io.devboxd.boxd_api.application.postAuthor;

import io.devboxd.boxd_api.infrastructure.CustomResponseEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/posts")
public interface PostAuthorController {
    @GetMapping("/authors/{authorId}")
    ResponseEntity<CustomResponseEntity> getPostsFromAuthor(@PathVariable Long authorId, Pageable pageable);
}
