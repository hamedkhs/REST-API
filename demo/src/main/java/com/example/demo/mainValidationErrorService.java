package com.example.demo;

public interface mainValidationErrorService {


    public void saveValidationError(String fieldName, String errorMessage) ;

    public void sendValidationErrorToElasticsearch(ValidationError validationError) ;
}
