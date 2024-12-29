package com.adriansanz.notasBackend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adriansanz.notasBackend.dto.LoginDTO;
import com.adriansanz.notasBackend.servicios.AuthServicio;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthServicio authServicio;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDTO loginDTO) {
        boolean isAuthenticated = authServicio.loginUsuario(loginDTO.getUsuario(), loginDTO.getPassword());

        if (isAuthenticated) {

            return ResponseEntity.ok("Valido");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales inválidas");

        }
    }
}
