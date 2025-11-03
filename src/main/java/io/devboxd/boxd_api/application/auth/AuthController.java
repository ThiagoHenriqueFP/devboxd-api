package io.devboxd.boxd_api.application.auth;

import io.devboxd.boxd_api.application.auth.dto.SignInDTO;
import io.devboxd.boxd_api.application.auth.dto.SignUpDTO;
import io.devboxd.boxd_api.infrastructure.CustomResponseEntity;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/auth")
public interface AuthController {
    @PostMapping("/signup")
    ResponseEntity<Void> signUp(@RequestBody @Valid SignUpDTO createUserDTO);

    @PostMapping("/signin")
    ResponseEntity<CustomResponseEntity> signIn(@RequestBody @Valid SignInDTO signInDTO);
}
