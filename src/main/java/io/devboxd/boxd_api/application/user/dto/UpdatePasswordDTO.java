package io.devboxd.boxd_api.application.user.dto;

public record UpdatePasswordDTO(
        String oldPassword,
        String newPassword
) {
}
