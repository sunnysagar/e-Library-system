package com.sunny.Book.Library.System.expection;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class RentalNotFoundException extends RuntimeException{
        public RentalNotFoundException(String message){
            super(message);
        }
}
