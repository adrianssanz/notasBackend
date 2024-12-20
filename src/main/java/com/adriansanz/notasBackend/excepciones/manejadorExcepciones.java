package com.adriansanz.notasBackend.excepciones;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class manejadorExcepciones {
    @ExceptionHandler(notaNoEncontradaException.class)
    public ResponseEntity<String> manejarNotaNoEncontradaException(notaNoEncontradaException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);  // Devuelve el mensaje con el código de estado 404
    }
}
