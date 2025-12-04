package io.devboxd.boxd_api.application.auth;

import io.devboxd.boxd_api.application.auth.dto.AuthCompleteDTO;
import io.devboxd.boxd_api.application.auth.dto.SignInDTO;
import io.devboxd.boxd_api.application.auth.dto.SignUpDTO;
import io.devboxd.boxd_api.domain.user.UserService;
import io.devboxd.boxd_api.infrastructure.CustomResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class AuthControllerImpl implements AuthController {
    private final UserService userService;

    public AuthControllerImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public ResponseEntity<Void> signUp(SignUpDTO createUserDTO) {
        this.userService.save(createUserDTO);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<CustomResponseEntity> signIn(SignInDTO signInDTO) {
        var user = this.userService.signIn(signInDTO);
        if (user.isEmpty())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);

        String jwt = this.userService.getJwt(user.get());

        return ResponseEntity.ok(new CustomResponseEntity(new AuthCompleteDTO(jwt, "Bearer")));
    }
}
