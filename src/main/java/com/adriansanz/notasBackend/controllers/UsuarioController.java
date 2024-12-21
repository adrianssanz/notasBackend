package com.adriansanz.notasBackend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.adriansanz.notasBackend.entidades.Usuario;
import com.adriansanz.notasBackend.servicios.UsuarioServicio;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioServicio usuarioServicio;

    @GetMapping
    public List<Usuario> getAllUsuarios(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {
        return usuarioServicio.getAllUsuarios(page, size);
    }

    @GetMapping("/{id}")
    public Usuario getUsuarioById(@PathVariable Long id) {
        return usuarioServicio.getUsuarioById(id);
    }   

    @PostMapping
    public Usuario createUsuario(@Valid @RequestBody Usuario usuario) {
        return usuarioServicio.createUsuario(usuario);
    }
}
