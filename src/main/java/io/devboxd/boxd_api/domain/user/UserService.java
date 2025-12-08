package io.devboxd.boxd_api.domain.user;

import io.devboxd.boxd_api.application.auth.dto.SignInDTO;
import io.devboxd.boxd_api.application.auth.dto.SignUpDTO;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> getUsersStartsWith(String prefix);
    User getUser(String username);
    User save(SignUpDTO dto);
    Optional<User> signIn(SignInDTO dto);

    User updatePassword(String username, String oldPasswd, String newPasswd);

    int shadowDelete(String username);

    String getJwt(User user);
}
