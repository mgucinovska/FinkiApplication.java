package com.project.finki.dto.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.PRECONDITION_FAILED)
public class ShoppingCartException extends RuntimeException {
    public ShoppingCartException(String message) {
        super(message);
    }
}