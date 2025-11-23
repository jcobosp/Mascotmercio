package es.upm.dit.isst.mascotmercioapiback.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class EstablecimientoNotFoundAdvice {
    @ResponseBody
    @ExceptionHandler(EstablecimientoNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String EstablecimientoNotFoundHandler(EstablecimientoNotFoundException ex) {
        return ex.getMessage();
    }
}