package com.adriansanz.notasBackend.servicios;

import java.util.List;

import org.springframework.stereotype.Service;

import com.adriansanz.notasBackend.entidades.Usuario;

@Service
public interface UsuarioServicio {
    List<Usuario> getAllUsuarios(int page, int size);

    Usuario getUsuarioById(Long id);

    Usuario createUsuario(Usuario usuario);
}
