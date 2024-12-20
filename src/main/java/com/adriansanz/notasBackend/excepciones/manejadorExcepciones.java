package com.adriansanz.notasBackend.excepciones;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class manejadorExcepciones {
    @ExceptionHandler(notaNoEncontradaException.class)
    public ResponseEntity<String> manejarNotaNoEncontradaException(notaNoEncontradaException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<String> manejarRutaNoEncontrada(NoHandlerFoundException ex){
        return new ResponseEntity<>("Ruta no encontrada: " + ex.getRequestURL(), HttpStatus.NOT_FOUND);
    }
}
