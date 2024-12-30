package com.adriansanz.notasBackend.servicios;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.adriansanz.notasBackend.dto.UsuarioDTO;

@Service
public interface UsuarioServicio {
    ResponseEntity<List<UsuarioDTO>> getAllUsuarios(int page, int size);

    ResponseEntity<UsuarioDTO> getUsuarioById(Long id);

    ResponseEntity<Void> deleteUsuario(Long id);
}
