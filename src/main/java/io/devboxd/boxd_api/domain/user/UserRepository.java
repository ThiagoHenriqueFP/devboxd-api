package io.devboxd.boxd_api.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

// criacao basica dos repositorios para acesso de dados de usuario
public interface UserRepository extends JpaRepository<User, Long> {
    // query para achar usuario baseado no username, jpa gera essa query com reflexao
    Optional<User> findByUsername(String username);

    // query feita na mao para achar usuario, porem com JPQL
    @Query("select u from User u where u.username = :username ")
    Optional<User> findByUsernameJPQL(String username);

    // query com sql puro
    @Query(value = "select * from users where users.username = :username", nativeQuery = true)
    Optional<User> findByUsernameRaw(String username);

}
