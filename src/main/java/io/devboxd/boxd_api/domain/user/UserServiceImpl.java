package io.devboxd.boxd_api.domain.user;

import io.devboxd.boxd_api.domain.util.PasswordRegexUtil;
import io.devboxd.boxd_api.infrastructure.exception.PasswordNotMatchException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getUsersStartsWith(String prefix) {
        return userRepository.findByUsernamePrefix(prefix);
    }

    @Override
    public User getUser(String username) {
        return userRepository.findByUsernameAndIsActive(username, true).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NO_CONTENT)
        );
    }

    @Override
    public User save(User user) {

        Optional<User> alreadySaved = userRepository.findByUsernameAndIsActive(user.getUsername(), true);

        if (alreadySaved.isEmpty())
            return userRepository.save(user);

        throw new ResponseStatusException(HttpStatus.CONFLICT, "The username is taken!");
    }

    @Override
    public User updatePassword(String username, String oldPasswd, String newPasswd) {
        List<String> reasons = PasswordRegexUtil.validate(newPasswd);
        
        if (!reasons.isEmpty())
            throw new PasswordNotMatchException(reasons);
        
        User user = this.getUser(username);

        if (!user.getPassword().equals(oldPasswd))
            throw new ResponseStatusException(HttpStatus.CONFLICT, "The old password is wrong!");

        // TODO hash the password

        user.setPassword(newPasswd);
        return userRepository.save(user);
    }

    @Override
    public boolean shadowDelete(String username) {
        return this.userRepository.shadowDelete(username);
    }
}
