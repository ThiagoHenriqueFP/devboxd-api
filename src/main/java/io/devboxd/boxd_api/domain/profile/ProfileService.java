package io.devboxd.boxd_api.domain.profile;

import java.util.List;

public interface ProfileService {
    List<Profile> getAllFollowers();

    List<Profile> getAllFollowing();

    Profile getByUsername(String username, List<Profile> listToGet);

}
