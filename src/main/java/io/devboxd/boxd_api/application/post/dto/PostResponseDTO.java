package io.devboxd.boxd_api.application.post.dto;

import io.devboxd.boxd_api.domain.photo.Photo;
import io.devboxd.boxd_api.domain.profile.Profile;

import java.time.LocalDateTime;
import java.util.List;

public record PostResponseDTO(
        String header,
        String body,
        Profile author,
        List<Photo> photos,
        Integer views,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
        ) {
}
