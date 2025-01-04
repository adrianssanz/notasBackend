package com.adriansanz.notasBackend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adriansanz.notasBackend.dto.LoginDTO;
import com.adriansanz.notasBackend.dto.UsuarioDTO;
import com.adriansanz.notasBackend.entidades.Usuario;
import com.adriansanz.notasBackend.servicios.AuthServicio;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthServicio authServicio;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDTO loginDTO, HttpSession session) {
        if (authServicio.loginUsuario(loginDTO.getUsuario(), loginDTO.getPassword(), session)) {
            return ResponseEntity.ok("Inicio de sesión exitoso");
        }
        return ResponseEntity.status(401).body("Usuario o contraseña incorrectos");
    }

    @PostMapping("/register")
    public ResponseEntity<UsuarioDTO> registerUsuario(@Valid @RequestBody Usuario usuario) {
        return authServicio.registerUsuario(usuario);
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logoutUsuario(HttpSession session) {
        authServicio.logoutUsuario(session);
        return ResponseEntity.ok("Sesión cerrada con exito");
    }
}
