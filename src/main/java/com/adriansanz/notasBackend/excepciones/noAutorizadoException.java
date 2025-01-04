package com.adriansanz.notasBackend.excepciones;

public class noAutorizadoException extends RuntimeException {
    public noAutorizadoException(String mensaje) {
        super(mensaje);
    }
}
