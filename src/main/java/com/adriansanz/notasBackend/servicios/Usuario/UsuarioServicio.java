package com.adriansanz.notasBackend.servicios.Usuario;

import java.util.List;

import org.springframework.stereotype.Service;

import com.adriansanz.notasBackend.dto.UsuarioDTO;
import com.adriansanz.notasBackend.entidades.Usuario;

@Service
public interface UsuarioServicio {
    List<UsuarioDTO> getAllUsuarios(int page, int size);

    UsuarioDTO getUsuarioById(Long id);

    UsuarioDTO createUsuario(Usuario usuario);

    void deleteUsuario(Long id);
}
