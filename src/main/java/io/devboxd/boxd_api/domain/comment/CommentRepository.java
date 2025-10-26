package io.devboxd.boxd_api.domain.comment;

import io.devboxd.boxd_api.domain.profile.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CommentRepository  extends JpaRepository<Comment, Long> {

    Optional<Comment> findByBody(String body);

    Optional<Comment> findByAuthor(Profile Author);

    @Query("SELECT c FROM Comment c WHERE c.author.user.username = :username")
    Optional<Comment> findByAuthorUsername(String username);

    @Query("SELECT c FROM Comment c WHERE c.author.id = :id")
    Optional<Comment> findByAuthorId(Long id);



}
