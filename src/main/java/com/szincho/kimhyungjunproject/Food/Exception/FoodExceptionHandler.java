package com.szincho.kimhyungjunproject.Food.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@ControllerAdvice(basePackages = "com.szincho.kimhyungjunproject.Food")
public class FoodExceptionHandler {

    @ExceptionHandler(FoodNotFoundException.class)
    public ResponseEntity<Object> handleOfFoodNotFoundException(FoodNotFoundException e) {
        HashMap<String, Object> body = new HashMap<>();
        body.put("error", e.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleOfRequestValidation(MethodArgumentNotValidException e) {
        HashMap<String, HashMap<String, String>> body = new HashMap<>();

        HashMap<String, String> errorMap = new HashMap<>();
        for (FieldError error : e.getBindingResult().getFieldErrors()) {
            errorMap.put(error.getField(), error.getDefaultMessage());
        }
        body.put("error", errorMap);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }

}
