package io.devboxd.boxd_api.domain.profile;

import io.devboxd.boxd_api.domain.post.Post;
import io.devboxd.boxd_api.domain.user.User;

import java.util.List;

public interface ProfileService {
    List<Profile> getAllFollowers(Long id);

    List<Profile> getAllFollowing(Long id);

    Profile getByUsername(String username);

    Profile getByUser(User user);

    Profile create(Profile profile);

    boolean delete(Long id);

    boolean update(Long id, Profile profile);


}
