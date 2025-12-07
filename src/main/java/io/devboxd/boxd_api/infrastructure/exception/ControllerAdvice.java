package io.devboxd.boxd_api.infrastructure.exception;

import io.devboxd.boxd_api.infrastructure.exception.dto.ExceptionDefaultDTO;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice {
    @ExceptionHandler({
            ResponseStatusException.class
    })
    protected ResponseEntity<Object> handleException(ResponseStatusException ex, WebRequest request) {
        ExceptionDefaultDTO<String> response = new ExceptionDefaultDTO<>(ex.getBody().getDetail());

        return new ResponseEntity<>(response, ex.getStatusCode());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<Object> handleMethodArgumentsNotValid(
            MethodArgumentNotValidException ex
    ) {
        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .toList();

        var response = new ExceptionDefaultDTO<>(errors);

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(PasswordNotMatchException.class)
    protected ResponseEntity<Object> handlePasswordNotMatchException(
            PasswordNotMatchException ex
    ) {
        var response = new ExceptionDefaultDTO<>(ex.reasons);

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
