package org.cna.hotel.service.services.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Map<String, Object>> notFoundExceptionHandler(ResourceNotFoundException ex) {
        Map<String, Object> exceptionMap = new HashMap<>();
        exceptionMap.put("message", ex.getMessage());
        exceptionMap.put("success", "false");
        exceptionMap.put("status", HttpStatus.NOT_FOUND);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionMap);

    }
}
