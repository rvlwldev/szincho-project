package com.szincho.kimhyungjunproject.Order.DTO.Exception;


public class IllegalOrderException extends IllegalArgumentException {
    public IllegalOrderException(String message) {
        super(message);
    }

}