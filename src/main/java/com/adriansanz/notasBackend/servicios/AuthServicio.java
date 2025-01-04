package com.adriansanz.notasBackend.servicios;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.adriansanz.notasBackend.dto.UsuarioDTO;
import com.adriansanz.notasBackend.entidades.Usuario;

import jakarta.servlet.http.HttpSession;

@Service
public interface AuthServicio {
    boolean loginUsuario(String usuario, String password, HttpSession session);

    void logoutUsuario(HttpSession session);

    Usuario getSesionUsuario(HttpSession session);

    ResponseEntity<UsuarioDTO> registerUsuario(Usuario usuario);
} 
