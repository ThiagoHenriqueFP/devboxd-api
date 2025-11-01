package io.devboxd.boxd_api.domain.like;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like, Long> {

    Optional<Like> findByProfileId(Long id);

    Optional<Like> findByContentId(Long id);
}
