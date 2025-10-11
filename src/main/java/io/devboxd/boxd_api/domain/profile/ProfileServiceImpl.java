package io.devboxd.boxd_api.domain.profile;

import io.devboxd.boxd_api.domain.user.User;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfileServiceImpl implements ProfileService{

    private final ProfileRepository profileRepository;

    public ProfileServiceImpl(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    @Override
    public List<Profile> getAllFollowers(Long id) {
        return profileRepository.findFollowersById(id);
    }

    @Override
    public List<Profile> getAllFollowing(Long id) {
        return profileRepository.findFollowingById(id);
    }


    @Override
    public Profile getByUsername(String username) {
        return profileRepository.findByUsername(username);
    }

    @Override
    public Profile getByUser(User user) {return profileRepository.findByUser(user);
    }

    @Override
    @Transactional
    @Modifying
    public Profile create(Profile profile) {
        return profileRepository.save(profile);
    }

    @Override
    @Transactional
    @Modifying
    public boolean delete(Long id) {
        if(!profileRepository.existsById(id)) return false;

        profileRepository.deleteById(id);
        return true;
    }

    @Override
    @Transactional
    @Modifying
    public boolean update(Long id, Profile profile) {
        Profile existing = profileRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Profile not found"));

        if (profile.getFirstName() != null) existing.setLastName(profile.getFirstName());
        if (profile.getLastName() != null) existing.setLastName(profile.getLastName());
        if (profile.getPhoto() != null) existing.setPhoto(profile.getPhoto());
        if (profile.getFollowers() != null) existing.setFollowers(profile.getFollowers());
        if (profile.getFollowing() != null) existing.setFollowing(profile.getFollowing());
        if (profile.getPosts() != null) existing.setPosts(profile.getPosts());

        profileRepository.save(existing);

        return true;
    }
}
