package com.adriansanz.notasBackend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.adriansanz.notasBackend.dto.UsuarioDTO;
import com.adriansanz.notasBackend.entidades.Usuario;
import com.adriansanz.notasBackend.excepciones.noAutorizadoException;
import com.adriansanz.notasBackend.servicios.AuthServicio;
import com.adriansanz.notasBackend.servicios.UsuarioServicio;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioServicio usuarioServicio;
    private AuthServicio authServicio;

    public UsuarioController(AuthServicio authServicio){
        this.authServicio = authServicio;
    }

    public void validarUsuarioAutenticado(HttpSession session){
        Usuario usuario = authServicio.getSesionUsuario(session);
        if (usuario.getRol().getId() != 1) {
            throw new noAutorizadoException("No tienes permisos para realizar esta acción.");
        }
    }

    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> getAllUsuarios(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size, HttpSession session) {
        validarUsuarioAutenticado(session);
        return usuarioServicio.getAllUsuarios(page, size);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> getUsuarioById(@PathVariable Long id, HttpSession session) {
        validarUsuarioAutenticado(session);
        return usuarioServicio.getUsuarioById(id);
    }  

    @PutMapping("/{id}/rol")
    public ResponseEntity<UsuarioDTO> updateRolUsuario(@PathVariable Long id, HttpSession session){
        validarUsuarioAutenticado(session);
        return usuarioServicio.updateRolUsuario(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUsuario(@PathVariable long id, HttpSession session){
        validarUsuarioAutenticado(session);
        usuarioServicio.deleteUsuario(id);
        return ResponseEntity.noContent().build();
    }
}
