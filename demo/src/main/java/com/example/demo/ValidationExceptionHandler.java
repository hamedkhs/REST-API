package com.example.demo;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ValidationExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(ValidationExceptionHandler.class);

    @Autowired
    private ValidationErrorService validationErrorService;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);

            ValidationError validationError = new ValidationError(fieldName, errorMessage);
            validationErrorService.saveValidationError(fieldName, errorMessage);

            // ثبت خطا به فرمت JSON در فایل
            logger.error("Validation error - Field: {}, Message: {}", fieldName, errorMessage);

            // ارسال خطا به Elasticsearch
            validationErrorService.sendValidationErrorToElasticsearch(validationError);
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}