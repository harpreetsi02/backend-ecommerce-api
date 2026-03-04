package com.practiceproject.ecommerce_api.exception;

public class ApiException extends RuntimeException{
    public ApiException(String message){
        super(message);
    }
}
