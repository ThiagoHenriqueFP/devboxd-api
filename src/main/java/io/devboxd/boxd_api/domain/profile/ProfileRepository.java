package io.devboxd.boxd_api.domain.profile;

import io.devboxd.boxd_api.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProfileRepository extends JpaRepository<Profile, Long> {

    @Override
    Optional<Profile> findById(Long id);

    Optional<Profile> findByUser(User user);

    @Query("SELECT p.followers FROM Profile p WHERE p.id = :id")
    List<Profile> findFollowersById(Long id);

    @Query("SELECT p.following FROM Profile p WHERE p.id = :id")
    List<Profile> findFollowingById(Long id);

    @Query("DElETE p.following FROM Profile p WHERE p.id = :id")
    List<Profile> deleteFollowingById(Long id);

    @Query("DElETE p.followers FROM Profile p WHERE p.id = :id")
    List<Profile> deleteFollowerById(Long id);
}
