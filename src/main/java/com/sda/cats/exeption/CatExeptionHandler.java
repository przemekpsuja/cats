package com.sda.cats.exeption;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CatExeptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CatNotFoundExeption.class)
    public ResponseEntity handleCatNotFoundExeption(){
        return ResponseEntity.notFound().build();
    }
}
