package io.devboxd.boxd_api.domain.like;

public interface LikeService {

    Like findByProfileId(Long id);

    Like findByContentId(Long id);
}
