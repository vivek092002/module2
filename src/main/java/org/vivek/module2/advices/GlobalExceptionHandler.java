package org.vivek.module2.advices;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.vivek.module2.exceptions.ResourceNotFoundExceptions;

import java.util.NoSuchElementException;

@RestControllerAdvice
public class GlobalExceptionHandler {
    //Exception Handler
    @ExceptionHandler(ResourceNotFoundExceptions.class)
    public ResponseEntity<ApiError> handleResourceNotFound(ResourceNotFoundExceptions exception) {
//        return new ResponseEntity<>("Resource not found", HttpStatus.NOT_FOUND);
        ApiError apiError = ApiError.builder()
                .status(HttpStatus.NOT_FOUND)
                .message(exception.getMessage())
                .build();
        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public  ResponseEntity<ApiError> handleInternalServerError(Exception exception){
        ApiError apiError = ApiError.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .message(exception.getMessage())
                .build();
        return new ResponseEntity<>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
