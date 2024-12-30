package com.adriansanz.notasBackend.servicios;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.adriansanz.notasBackend.dto.UsuarioDTO;
import com.adriansanz.notasBackend.entidades.Usuario;

@Service
public interface AuthServicio {
    boolean loginUsuario(String usuario, String password);

    ResponseEntity<UsuarioDTO> registerUsuario(Usuario usuario);
} 
