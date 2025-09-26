package io.devboxd.boxd_api.domain.user;

import java.util.List;

public interface UserService {
    List<User> getUsersStartsWith(String prefix);
    User getUser(String username);
}
