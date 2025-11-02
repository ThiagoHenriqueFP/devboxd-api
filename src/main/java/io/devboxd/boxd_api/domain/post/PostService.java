package io.devboxd.boxd_api.domain.post;

import io.devboxd.boxd_api.domain.profile.Profile;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PostService {

    List<Post> getAllPostByAuthor(Profile author);

    List<Post> getAllPostByAuthorId(Long id, Pageable pageable);

    List<Post> getAllPostByAuthorUsername(String username);

    Post getPostById(Long id);

    Post getPostByBody(String body);
}
