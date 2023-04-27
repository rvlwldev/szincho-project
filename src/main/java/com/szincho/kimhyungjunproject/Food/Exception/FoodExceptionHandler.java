package com.szincho.kimhyungjunproject.Food.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;

@ControllerAdvice
public class FoodExceptionHandler {

    @ExceptionHandler(FoodNotFoundException.class)
    public ResponseEntity<Object> handleOfFoodNotFoundException(FoodNotFoundException e) {
        HashMap<String, Object> body = new HashMap<>();
        body.put("error", e.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
    }
}
