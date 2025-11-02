package io.devboxd.boxd_api.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
// criacao basica dos repositorios para acesso de dados de usuario
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsernameAndIsActive(String username, Boolean isActive);

    @Query("update User u set u.isActive = false where u.username = :username")
    boolean shadowDelete(String username);

    @Query("select u from User u where u.username ilike :prefix and u.isActive = true")
    List<User> findByUsernamePrefix(String prefix);
}
