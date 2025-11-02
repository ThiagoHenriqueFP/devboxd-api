package io.devboxd.boxd_api.application.post;

import io.devboxd.boxd_api.infrastructure.CustomResponseEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/posts")
public interface PostController {

    @GetMapping("/{id}")
    ResponseEntity<CustomResponseEntity> getPostById(@PathVariable Long id);
}
