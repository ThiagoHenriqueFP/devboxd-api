package io.devboxd.boxd_api.domain.post;

import io.devboxd.boxd_api.domain.profile.Profile;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
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
    public List<Post> getAllPostByAuthorId(Long id, Pageable pageable) {
        return postRepository.findPostsByAuthor_Id(id, pageable);
    }

    @Override
    public List<Post> getAllPostByAuthorUsername(String username) {
        return postRepository.findByAuthorUsername(username);
    }

    @Override
    public Post getPostByBody(String body) {
        return this.postRepository.findByBody(body).orElseThrow( () -> new EntityNotFoundException("post not found"));
    }

    @Override
    public Post getPostById(Long id) {
        return this.postRepository.findById(id).orElseThrow( () -> new EntityNotFoundException("post not found"));
    }
}
