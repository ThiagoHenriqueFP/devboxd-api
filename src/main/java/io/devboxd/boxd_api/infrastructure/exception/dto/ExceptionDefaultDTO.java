package io.devboxd.boxd_api.infrastructure.exception.dto;

import java.time.LocalDateTime;

public record ExceptionDefaultDTO<T>(
        T data,
        String timestamp
) {
    public ExceptionDefaultDTO(T data) {
        this(data, LocalDateTime.now().toString());
    }
}
