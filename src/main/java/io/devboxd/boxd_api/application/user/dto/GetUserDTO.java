package io.devboxd.boxd_api.application.user.dto;

import io.devboxd.boxd_api.domain.user.User;

import java.time.LocalDateTime;

public record GetUserDTO(
        String username,
        String email,
        String createdAt
) {

    public GetUserDTO(String username, String email, LocalDateTime createdAt) {
        this(username, email, createdAt.toString());
    }

    static GetUserDTO fromUser(User user) {
        return new GetUserDTO(user.getUsername(), user.getEmail(), LocalDateTime.now());
    }
}
