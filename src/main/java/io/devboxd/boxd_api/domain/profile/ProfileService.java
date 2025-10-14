package io.devboxd.boxd_api.domain.profile;

import io.devboxd.boxd_api.domain.user.User;
import java.util.List;
import java.util.Optional;

public interface ProfileService {
    List<Profile> getAllFollowers(Long id);

    List<Profile> getAllFollowing(Long id);

    Optional<Profile> getByUsername(String username);

    Optional<Profile> getByUser(User user);

    Profile create(Profile profile);

    boolean delete(Long id);

    boolean update(Long id, Profile profile);


}
