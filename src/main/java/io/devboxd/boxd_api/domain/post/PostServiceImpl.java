package io.devboxd.boxd_api.domain.post;

import io.devboxd.boxd_api.domain.profile.Profile;

import java.util.List;

public class PostServiceImpl implements PostService{

    private final PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public List<Post> getAllPostByAuthor(Profile author) {
        return postRepository.findByAuthor(author);
    }

    @Override
    public List<Post> getAllPostByAuthorId(Long id) {
        return List.of();
    }
}
