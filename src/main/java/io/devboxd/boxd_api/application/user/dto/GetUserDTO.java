package io.devboxd.boxd_api.application.user.dto;

import io.devboxd.boxd_api.domain.user.User;
import lombok.Builder;

@Builder
public record GetUserDTO(
        String username,
        String email
) {

    static GetUserDTO fromUser(User user) {
        // esse padrao builder so serve para 'facilitar' a visualizacao, mas poderia ser
        // new GetUserDTO(user.getUsername, user.getEmail()) sem prejuizos
        return GetUserDTO
                .builder()
                .username(user.getUsername())
                .email(user.getEmail())
                .build();
    }
}
