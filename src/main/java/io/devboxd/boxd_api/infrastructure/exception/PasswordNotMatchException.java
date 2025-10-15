package io.devboxd.boxd_api.infrastructure.exception;

import java.util.List;

public class PasswordNotMatchException extends RuntimeException {
    public List<String> reasons;

    public PasswordNotMatchException(List<String> reasons) {
        super(reasons.toString());
        this.reasons = reasons;
    }
}
