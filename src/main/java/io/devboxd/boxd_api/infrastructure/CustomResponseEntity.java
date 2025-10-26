package io.devboxd.boxd_api.infrastructure;

import java.time.LocalDateTime;

public record CustomResponseEntity(Object body, String timestamp) {
    public CustomResponseEntity(Object body) {
        this(body, LocalDateTime.now().toString());
    }
}
