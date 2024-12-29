package com.adriansanz.notasBackend.servicios;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.adriansanz.notasBackend.dto.UsuarioDTO;
import com.adriansanz.notasBackend.entidades.Usuario;

@Service
public interface UsuarioServicio {
    ResponseEntity<List<UsuarioDTO>> getAllUsuarios(int page, int size);

    ResponseEntity<UsuarioDTO> getUsuarioById(Long id);

    ResponseEntity<UsuarioDTO> createUsuario(Usuario usuario);

    ResponseEntity<Void> deleteUsuario(Long id);
}
