package com.adriansanz.notasBackend.excepciones;

public class elementoNoEncontradoException extends RuntimeException {

    public elementoNoEncontradoException(Long id, String mensaje) {
        super(mensaje + id);
    }
}
