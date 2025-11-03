package io.devboxd.boxd_api.domain.like;

import io.devboxd.boxd_api.domain.comment.Comment;
import io.devboxd.boxd_api.domain.comment.CommentRepository;
import io.devboxd.boxd_api.domain.post.Post;
import io.devboxd.boxd_api.domain.post.PostRepository;
import io.devboxd.boxd_api.domain.profile.Profile;
import jakarta.persistence.EntityNotFoundException;

public class LikeServiceImpl implements LikeService{

    private final LikeRepository likeRepository;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    public LikeServiceImpl(LikeRepository likeRepository, PostRepository postRepository, CommentRepository commentRepository){
        this.likeRepository = likeRepository;
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
    }

    @Override
    public Like findByProfileId(Long id) {
        return likeRepository.findByProfileId(id).orElseThrow( () -> new EntityNotFoundException("Like não encontrado."));
    }

    @Override
    public Like make(Profile author, Long postId, Long commentId) {
        if ((postId != null && commentId != null) || (postId == null && commentId == null)) {
            throw new IllegalArgumentException("O like deve ser dado em UM ÚNICO item.");
        }

        Like like = new Like();
        like.setProfile(author);

        if(postId != null){
            Post post = postRepository.findById(postId).orElseThrow(() -> new EntityNotFoundException("Post não encontrado."));
            like.setPost(post);
        } else {
            Comment comment = commentRepository.findById(commentId).orElseThrow( () -> new EntityNotFoundException("Comment não encontrado."));
            like.setComment(comment);
        }

        return likeRepository.save(like);
    }

    @Override
    public Long countByPostId(Long id) {
        return likeRepository.countByPostId(id);
    }

    @Override
    public Long countByCommentId(Long id) {
        return likeRepository.countByCommentId(id);
    }
}
