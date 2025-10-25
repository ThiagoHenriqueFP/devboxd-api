package io.devboxd.boxd_api.domain.post;

import io.devboxd.boxd_api.domain.profile.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findByAuthor(Profile author);

    @Query("SELECT p FROM Post p WHERE p.author.user.username = :username")
    List<Post> findByAuthorUsername(String username);

    @Query("SELECT p FROM Post p WHERE p.author.id = :id")
    List<Post> findByAuthorId(@Param("id") Long id);

    Optional<Post> findByBody(String body);



}
