package io.devboxd.boxd_api.domain.profile;

import java.util.List;

public class ProfileServiceImpl implements ProfileService{
    @Override
    public List<Profile> getAllFollowers() {
        return List.of();
    }

    @Override
    public List<Profile> getAllFollowing() {
        return List.of();
    }

    @Override
    public Profile getByUsername(String username) {
        return null;
    }

    @Override
    public Profile create(Profile profile) {
        return null;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    @Override
    public Profile update(Long id, Profile profile) {
        return null;
    }
}
