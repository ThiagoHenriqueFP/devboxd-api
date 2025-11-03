package io.devboxd.boxd_api.domain.like;

import io.devboxd.boxd_api.domain.profile.Profile;

public interface LikeService {

    Like findByProfileId(Long id);

    Like make(Profile author, Long postId, Long commentId);

    Long countByPostId(Long id);

    Long countByCommentId(Long id);
}
