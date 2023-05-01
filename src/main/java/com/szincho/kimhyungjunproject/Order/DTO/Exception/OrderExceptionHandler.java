package com.szincho.kimhyungjunproject.Order.DTO.Exception;

import com.szincho.kimhyungjunproject.Food.Exception.FoodNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.HashSet;

@ControllerAdvice(basePackages = "com.szincho.kimhyungjunproject.Order")
public class OrderExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleOfRequestValidation(MethodArgumentNotValidException e) {
        HashSet<String> body = new HashSet<>();

        for (FieldError error : e.getBindingResult().getFieldErrors()) {
            body.add(error.getDefaultMessage());
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new HashMap<>() {{
            for (String message : body) put("error", message);
        }});
    }

    @ExceptionHandler({OrderNotFoundException.class, IllegalOrderException.class})
    public ResponseEntity<Object> handleOfInvalidOrderException(IllegalArgumentException e) {
        HashMap<String, Object> body = new HashMap<>() {{
            put("error", e.getMessage());
        }};

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
    }

    @ExceptionHandler(FoodNotFoundException.class)
    public ResponseEntity<Object> handleOfFoodNotFoundException(FoodNotFoundException e) {
        HashMap<String, Object> body = new HashMap<>();
        body.put("error", e.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
    }


}
