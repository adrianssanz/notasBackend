package com.adriansanz.notasBackend.excepciones;

public class noAutenticadoException extends RuntimeException {
    public noAutenticadoException(String mensaje) {
        super(mensaje);
    }
}