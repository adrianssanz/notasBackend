package com.adriansanz.notasBackend.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.adriansanz.notasBackend.entidades.Usuario;
import com.adriansanz.notasBackend.excepciones.elementoNoEncontradoException;
import com.adriansanz.notasBackend.excepciones.usuarioDuplicadoException;
import com.adriansanz.notasBackend.repositorios.UsuarioRepositorio;

@Service
public class UsuarioServicioImpl implements UsuarioServicio {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Override
    public List<Usuario> getAllUsuarios(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return usuarioRepositorio.findAll(pageable).getContent();
    }

    @Override
public Usuario createUsuario(Usuario usuario) {
    usuarioRepositorio.findByUsuario(usuario.getUsuario())
        .ifPresent(u -> {
            throw new usuarioDuplicadoException();
        });

    return usuarioRepositorio.save(usuario);
}

    @Override
    public Usuario getUsuarioById(Long id) {
        return usuarioRepositorio.findById(id)
                .orElseThrow(()-> new elementoNoEncontradoException(id, "Usuario no encontrado con id: "));
    }

    
}
