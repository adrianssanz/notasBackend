package com.adriansanz.notasBackend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.adriansanz.notasBackend.dto.UsuarioDTO;
import com.adriansanz.notasBackend.servicios.UsuarioServicio;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioServicio usuarioServicio;

    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> getAllUsuarios(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {
        return usuarioServicio.getAllUsuarios(page, size);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> getUsuarioById(@PathVariable Long id) {
        return usuarioServicio.getUsuarioById(id);
    }   

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUsuario(@PathVariable long id){
        usuarioServicio.deleteUsuario(id);
        return ResponseEntity.noContent().build();
    }
}
