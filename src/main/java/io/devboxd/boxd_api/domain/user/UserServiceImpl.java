package io.devboxd.boxd_api.domain.user;

import io.devboxd.boxd_api.application.auth.dto.SignInDTO;
import io.devboxd.boxd_api.application.auth.dto.SignUpDTO;
import io.devboxd.boxd_api.domain.util.PasswordRegexUtil;
import io.devboxd.boxd_api.infrastructure.config.security.PasswordEncoderImpl;
import io.devboxd.boxd_api.infrastructure.config.security.SecurityCustomConfiguration;
import io.devboxd.boxd_api.infrastructure.config.security.jwt.JwtTokenService;
import io.devboxd.boxd_api.infrastructure.config.userDetails.UserDetailsImpl;
import io.devboxd.boxd_api.infrastructure.exception.PasswordNotMatchException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final PasswordEncoderImpl passwordEncoder;
    private final UserRepository userRepository;
    private final JwtTokenService jwtTokenService;

    public UserServiceImpl(PasswordEncoderImpl passwordEncoder, UserRepository userRepository, JwtTokenService jwtTokenService) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.jwtTokenService = jwtTokenService;
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
    public User save(SignUpDTO dto) {
        Optional<User> alreadySaved = userRepository.findByUsernameAndIsActive(dto.username(), true);

        if (alreadySaved.isPresent())
            throw new ResponseStatusException(HttpStatus.CONFLICT, "The username is taken!");

        User user = new User();
        user.setEmail(dto.email());
        user.setUsername(dto.username());
        user.setPassword(passwordEncoder.passwordEncoder().encode(dto.passwd()));

        return userRepository.save(user);

    }

    @Override
    public Optional<User> signIn(SignInDTO dto) {
        Optional<User> alreadySaved = userRepository.findByUsernameAndIsActive(dto.username(), true);

        if (alreadySaved.isEmpty())
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "The username or password is incorrect!");

        if (passwordEncoder.passwordEncoder().matches(dto.password(), alreadySaved.get().getPassword()))
            return alreadySaved;

        return Optional.empty();
    }

    private String hashPassword(String raw) {
        List<String> errors = PasswordRegexUtil.validate(raw);

        if (!errors.isEmpty())
            throw new PasswordNotMatchException(errors);

        return this.passwordEncoder.passwordEncoder().encode(raw);
    }

    @Override
    public User updatePassword(String username, String oldPasswd, String newPasswd) {
        List<String> reasons = PasswordRegexUtil.validate(newPasswd);
        
        if (!reasons.isEmpty())
            throw new PasswordNotMatchException(reasons);
        
        User user = this.getUser(username);
        String hashedPassword = hashPassword(newPasswd);
        if (this.passwordEncoder.passwordEncoder().matches(hashedPassword, user.getPassword()))
            throw new ResponseStatusException(HttpStatus.CONFLICT, "The old password is wrong!");

        user.setPassword(hashedPassword);
        return userRepository.save(user);
    }

    @Override
    public boolean shadowDelete(String username) {
        return this.userRepository.shadowDelete(username);
    }

    @Override
    public String getJwt(User user) {
        UserDetailsImpl details = new UserDetailsImpl(user);
        return this.jwtTokenService.generateToken(details);
    }
}
