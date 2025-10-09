package io.devboxd.boxd_api.application.user.dto;

import io.devboxd.boxd_api.domain.user.User;

public record GetUserDTO(
        String username,
        String email
) {

    static GetUserDTO fromUser(User user) {
        return new GetUserDTO(user.getUsername(), user.getEmail());
    }
}
