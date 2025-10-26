package io.devboxd.boxd_api.application.auth.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Valid
public record SignUpDTO(
        @NotBlank
        String username,
        @NotBlank
        @Email
        String email,
        @NotBlank
        String passwd
) {
}
