package io.devboxd.boxd_api.domain.profile;

import io.devboxd.boxd_api.domain.user.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProfileRepository extends JpaRepository<Profile, Long> {

    @Override
    Optional<Profile> findById(Long id);

    Optional<Profile> findByUser(User user);

    Optional<Profile> findByUsername(String username);

    @Query("SELECT p.followers FROM Profile p WHERE p.id = :id")
    List<Profile> findFollowersById(Long id);

    @Query("SELECT p.following FROM Profile p WHERE p.id = :id")
    List<Profile> findFollowingById(Long id);

    @Modifying
    @Transactional
    @Query("DELETE FROM Profile p WHERE p.id = :id")
    int deleteProfileById(Long id);

    @Modifying
    @Transactional
    @Query("""
        UPDATE Profile p
        SET
            p.firstName = COALESCE(:firstName, p.firstName),
            p.lastName = COALESCE(:lastName, p.lastName),
            p.bio = COALESCE(:bio, p.bio),
            p.photo = COALESCE(:photo, p.photo)
        WHERE p.id = :id
    """)
    int updateProfileFields(
            @Param("id") Long id,
            @Param("firstName") String firstName,
            @Param("lastName") String lastName,
            @Param("bio") String bio,
            @Param("photo") String photo
    );

}
