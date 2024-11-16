package com.learn.journalapp.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
