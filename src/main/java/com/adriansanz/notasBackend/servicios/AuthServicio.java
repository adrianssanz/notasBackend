package com.adriansanz.notasBackend.servicios;

import org.springframework.stereotype.Service;

@Service
public interface AuthServicio {
    boolean loginUsuario(String usuario, String password);
} 
