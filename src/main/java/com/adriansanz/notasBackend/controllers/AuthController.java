package com.adriansanz.notasBackend.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adriansanz.notasBackend.dto.LoginDTO;
import com.adriansanz.notasBackend.dto.UsuarioDTO;
import com.adriansanz.notasBackend.entidades.Usuario;
import com.adriansanz.notasBackend.excepciones.noAutenticadoException;
import com.adriansanz.notasBackend.servicios.AuthServicio;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthServicio authServicio;

    @PostMapping("/login")
public ResponseEntity<Map<String, String>> login(@RequestBody LoginDTO loginDTO, HttpSession session) {
    Map<String, String> response = new HashMap<>();
    if (authServicio.loginUsuario(loginDTO.getUsuario(), loginDTO.getPassword(), session)) {
        response.put("message", "Inicio de sesión exitoso");
        return ResponseEntity.ok(response);
    }
    response.put("message", "Usuario o contraseña incorrectos");
    return ResponseEntity.status(401).body(response);
}
    @GetMapping("/sesion")
    public ResponseEntity<Usuario> getUsuarioSesion(HttpSession session) {
        try {
            Usuario usuario = authServicio.getSesionUsuario(session);
            return ResponseEntity.ok(usuario);
        } catch (noAutenticadoException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
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
