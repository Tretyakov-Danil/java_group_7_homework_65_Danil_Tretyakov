package edu.attractor.onlinestore.exceptions;

import edu.attractor.onlinestore.controllers.ClientController;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.stream.Collectors;

@ControllerAdvice(basePackageClasses = {ClientController.class})
public class BindExceptionHandler{

    @ExceptionHandler(BindException.class)
    protected ResponseEntity<?> handleBind(BindException ex){
        var apiFieldErrors = ex.getFieldErrors().stream()
                .map(fieldError -> String.format( "%s -> %s",
                        fieldError.getField(),
                        fieldError.getDefaultMessage()))
                .collect(Collectors.toList());
        return ResponseEntity.unprocessableEntity()
                .body(apiFieldErrors);
    }
}