package io.devboxd.boxd_api.domain.post;

import io.devboxd.boxd_api.domain.profile.Profile;

import java.util.List;

public interface PostService {

    List<Post> getAllPostByAuthor(Profile author);

    List<Post> getAllPostByAuthorId(Long id);

    List<Post> getAllPostByAuthorUsername(String username);

    Post getPostByBody(String body);
}
