package com.adriansanz.notasBackend.excepciones;

public class notaNoEncontradaException extends RuntimeException {

    public notaNoEncontradaException(Long id) {
        super("Nota no encontrada con ID: " + id);
    }
}
