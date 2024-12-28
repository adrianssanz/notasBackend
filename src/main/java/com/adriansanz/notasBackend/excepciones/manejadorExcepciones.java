package com.adriansanz.notasBackend.excepciones;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.adriansanz.notasBackend.dto.ErrorResponseDTO;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class manejadorExcepciones {
    @ExceptionHandler(elementoNoEncontradoException.class)
public ResponseEntity<ErrorResponseDTO> manejarElementoNoEncontradoException(elementoNoEncontradoException ex, HttpServletRequest request) {
    ErrorResponseDTO errorResponse = new ErrorResponseDTO(
            LocalDateTime.now(),
            HttpStatus.NOT_FOUND.value(),
            "Not Found",
            ex.getMessage(),
            request.getRequestURI()
    );

    return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
}


    @ExceptionHandler(usuarioDuplicadoException.class)
    public ResponseEntity<ErrorResponseDTO> manejarUsuarioDuplicadoException(usuarioDuplicadoException ex,HttpServletRequest request) {
        ErrorResponseDTO errorResponse = new ErrorResponseDTO(
                LocalDateTime.now(),
                HttpStatus.CONFLICT.value(),
                "Conflict",
                ex.getMessage(),
                request.getRequestURI()
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<ErrorResponseDTO> manejarRutaNoEncontrada(NoHandlerFoundException ex) {
        ErrorResponseDTO errorResponse = new ErrorResponseDTO(
                LocalDateTime.now(), HttpStatus.NOT_FOUND.value(), "Not Found", "La ruta solicitada no existe",
                ex.getRequestURL());

        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult()
                .getAllErrors()
                .stream()
                .map(error -> error.getDefaultMessage())
                .collect(Collectors.toList());
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}
