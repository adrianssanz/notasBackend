package com.adriansanz.notasBackend.excepciones;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.adriansanz.notasBackend.dto.ErrorResponseDTO;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class manejadorExcepciones {
    @ExceptionHandler(elementoNoEncontradoException.class)
    public ResponseEntity<ErrorResponseDTO> manejarElementoNoEncontradoException(elementoNoEncontradoException ex,
            HttpServletRequest request) {
        ErrorResponseDTO errorResponse = new ErrorResponseDTO(
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                "Not Found",
                ex.getMessage(),
                request.getRequestURI());

        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ErrorResponseDTO> manejarMetodoNoPermitido(HttpRequestMethodNotSupportedException ex,
            HttpServletRequest request) {
        String path = request.getRequestURI();

        String method = request.getMethod();
        ErrorResponseDTO errorResponse = new ErrorResponseDTO(
            LocalDateTime.now(),
            HttpStatus.METHOD_NOT_ALLOWED.value(),
            "Method Not Allowed",
            "El método de solicitud '" + method + "' no está permitido",
            path);

        return new ResponseEntity<>(errorResponse, HttpStatus.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler(idInvalidoException.class)
    public ResponseEntity<ErrorResponseDTO> manejarIdInvalidoException(idInvalidoException ex,
            HttpServletRequest request) {
        ErrorResponseDTO errorResponse = new ErrorResponseDTO(
                LocalDateTime.now(),
                HttpStatus.CONFLICT.value(),
                "Conflict",
                ex.getMessage(),
                request.getRequestURI());

        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(NumberFormatException.class)
    public ResponseEntity<ErrorResponseDTO> manejarNumberFormatException(NumberFormatException ex,
        HttpServletRequest request) {
        ErrorResponseDTO errorResponse = new ErrorResponseDTO(
            LocalDateTime.now(),
            HttpStatus.BAD_REQUEST.value(),
            "Bad Request",
            "El formato del número es inválido",
            request.getRequestURI());

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(usuarioDuplicadoException.class)
    public ResponseEntity<ErrorResponseDTO> manejarUsuarioDuplicadoException(usuarioDuplicadoException ex,
            HttpServletRequest request) {
        ErrorResponseDTO errorResponse = new ErrorResponseDTO(
                LocalDateTime.now(),
                HttpStatus.CONFLICT.value(),
                "Conflict",
                ex.getMessage(),
                request.getRequestURI());

        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(passwordInvalidaException.class)
    public ResponseEntity<ErrorResponseDTO> manejarPasswordInvalidaException(passwordInvalidaException ex,
            HttpServletRequest request) {
        ErrorResponseDTO errorResponse = new ErrorResponseDTO(
                LocalDateTime.now(),
                HttpStatus.CONFLICT.value(),
                "Conflict",
                ex.getMessage(),
                request.getRequestURI());

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
