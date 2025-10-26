package io.devboxd.boxd_api.application.auth.dto;

import jakarta.validation.constraints.NotBlank;

public record SignInDTO(
        @NotBlank
        String username,
        @NotBlank
        String password
) {
}
