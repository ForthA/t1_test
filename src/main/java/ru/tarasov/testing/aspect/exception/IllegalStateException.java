package ru.tarasov.testing.aspect.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class IllegalStateException extends RuntimeException {
    public IllegalStateException() {
        super();
    }
    public IllegalStateException(String message) {
        super(message);
    }
    public IllegalStateException(String message, Throwable cause) {
        super(message, cause);
    }
}
