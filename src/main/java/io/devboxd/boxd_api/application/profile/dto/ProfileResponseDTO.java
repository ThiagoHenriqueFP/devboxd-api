package io.devboxd.boxd_api.application.profile.dto;

import jakarta.persistence.Column;

public record ProfileResponseDTO(
        String photo,
        String firstName,
        String lastName,
        String bio
) {
}
