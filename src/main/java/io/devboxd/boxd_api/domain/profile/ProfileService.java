package io.devboxd.boxd_api.domain.profile;

import io.devboxd.boxd_api.domain.post.Post;

import java.util.List;

public interface ProfileService {
    List<Profile> getAllFollowers();

    List<Profile> getAllFollowing();

    List<Post> getAllPost();

    Profile getByUsername(String username, List<Profile> listToGet);

}
