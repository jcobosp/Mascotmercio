package es.upm.dit.isst.mascotmercioapiback.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ValoracionNotFoundAdvice {
    @ResponseBody
    @ExceptionHandler(ValoracionNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String valoracionNotFoundHandler(ValoracionNotFoundException ex) {
        return ex.getMessage();
    }
}
