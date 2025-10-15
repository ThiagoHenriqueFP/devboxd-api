package io.devboxd.boxd_api.domain.user;

import java.util.List;

public interface UserService {
    List<User> getUsersStartsWith(String prefix);
    User getUser(String username);
    User save(User user);

    User updatePassword(String username, String oldPasswd, String newPasswd);

    boolean shadowDelete(String username);
}
