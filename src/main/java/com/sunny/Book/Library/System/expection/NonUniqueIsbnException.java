package com.sunny.Book.Library.System.expection;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NonUniqueIsbnException extends RuntimeException {
    public NonUniqueIsbnException(String message) {
        super(message);
    }
}
