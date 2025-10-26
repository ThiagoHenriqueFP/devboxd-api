package io.devboxd.boxd_api.application.auth.dto;

public record AuthCompleteDTO(
        String token,
        String type
) {
}
