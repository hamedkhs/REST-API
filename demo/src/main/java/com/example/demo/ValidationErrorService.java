package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ValidationErrorService implements mainValidationErrorService{

    @Autowired
    private ValidationErrorRepository validationErrorRepository;

    public void saveValidationError(String fieldName, String errorMessage) {
        ValidationError validationError = new ValidationError(fieldName, errorMessage);
        validationErrorRepository.save(validationError);
    }

    public void sendValidationErrorToElasticsearch(ValidationError validationError) {
        validationErrorRepository.save(validationError);
    }
}
