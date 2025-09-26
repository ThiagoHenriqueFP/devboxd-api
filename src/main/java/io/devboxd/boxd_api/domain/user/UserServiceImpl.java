package io.devboxd.boxd_api.domain.user;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Override
    public List<User> getUsersStartsWith(String prefix) {
        return List.of();
    }

    @Override
    public User getUser(String username) {
        return null;
    }
}
