package io.devboxd.boxd_api.domain.profile;

import io.devboxd.boxd_api.domain.post.Post;

import java.util.List;

public interface ProfileService {
    List<Profile> getAllFollowers();

    List<Profile> getAllFollowing();

    Profile getByUsername(String username);

    Profile create(Profile profile);

    boolean delete(Long id);

    Profile update(Long id, Profile profile);
}
