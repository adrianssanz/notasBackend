package com.adriansanz.notasBackend.excepciones;

public class passwordInvalidaException extends RuntimeException {
    public passwordInvalidaException() {
        super("La contraseña debe tener al menos 8 caracteres, una letra y un número.");
    }
}
