package com.fpt.swd391.fall2022.swd391.exception;

public class ApiDeniedException extends RuntimeException{

    public ApiDeniedException(String message) {
        super(message);
    }

    public ApiDeniedException(String message, Throwable cause) {
        super(message, cause);
    }
}
