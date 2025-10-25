package io.devboxd.boxd_api.domain.comments;

import io.devboxd.boxd_api.domain.profile.Profile;
import jakarta.persistence.EntityNotFoundException;

public class CommentServiceImpl implements CommentService{

    private final CommentRepository commentRepository;

    public CommentServiceImpl(CommentRepository commentRepository){
        this.commentRepository = commentRepository;
    }

    @Override
    public Comment getByAuthor(Profile author) {
        return commentRepository.findByAuthor(author).orElseThrow( () -> new EntityNotFoundException("comment not found"));
    }

    @Override
    public Comment getByAuthorId(Long id) {
        return commentRepository.findByAuthorId(id).orElseThrow( () -> new EntityNotFoundException("comment not found"));
    }

    @Override
    public Comment getByAuthorUsername(String username) {
        return commentRepository.findByAuthorUsername(username).orElseThrow( () -> new EntityNotFoundException("comment not found"));
    }

    @Override
    public Comment getByBody(String body) {
        return commentRepository.findByBody(body).orElseThrow( () -> new EntityNotFoundException("comment not found"));
    }
}
