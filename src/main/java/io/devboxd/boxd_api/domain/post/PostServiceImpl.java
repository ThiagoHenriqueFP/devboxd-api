package io.devboxd.boxd_api.domain.post;

import io.devboxd.boxd_api.domain.profile.Profile;
import jakarta.persistence.EntityNotFoundException;

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
        return postRepository.findByAuthorId(id);
    }

    @Override
    public List<Post> getAllPostByAuthorUsername(String username) {
        return postRepository.findByAuthorUsername(username);
    }

    @Override
    public Post getPostByBody(String body) {
        return this.postRepository.findByBody(body).orElseThrow( () -> new EntityNotFoundException("post not found"));
    }
}
