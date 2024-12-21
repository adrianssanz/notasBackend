package com.adriansanz.notasBackend.excepciones;

public class usuarioDuplicadoException extends RuntimeException {
    public usuarioDuplicadoException() {
        super("El usuario ya existe.");
    }
}
