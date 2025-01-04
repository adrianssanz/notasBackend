package com.adriansanz.notasBackend.excepciones;

public class UsuarioNoAutenticadoException extends RuntimeException {
    public UsuarioNoAutenticadoException(String mensaje) {
        super(mensaje);
    }
}