package com.adriansanz.notasBackend.servicios.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.adriansanz.notasBackend.dto.UsuarioDTO;
import com.adriansanz.notasBackend.entidades.Usuario;
import com.adriansanz.notasBackend.excepciones.elementoNoEncontradoException;
import com.adriansanz.notasBackend.excepciones.idInvalidoException;
import com.adriansanz.notasBackend.excepciones.usuarioDuplicadoException;
import com.adriansanz.notasBackend.mappers.UsuarioMapper;
import com.adriansanz.notasBackend.repositorios.UsuarioRepositorio;
import com.adriansanz.notasBackend.servicios.UsuarioServicio;

@Service
public class UsuarioServicioImpl implements UsuarioServicio {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Override
    public ResponseEntity<List<UsuarioDTO>> getAllUsuarios(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        List<Usuario> usuarios = usuarioRepositorio.findAll(pageable).getContent();
        return ResponseEntity.status(HttpStatus.OK).body(UsuarioMapper.toUsuarioDTOList(usuarios));
    }

    @Override
    public ResponseEntity<UsuarioDTO> createUsuario(Usuario usuario) {
        usuarioRepositorio.findByUsuario(usuario.getUsuario())
                .ifPresent(u -> {
                    throw new usuarioDuplicadoException();
                });

        if (!usuario.getPassword().matches("^(?=.*[A-Za-z])(?=.*\\d).{8,}$")) {
            throw new IllegalArgumentException(
                    "La contraseña debe tener al menos 8 caracteres, una letra y un número.");
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(usuario.getPassword());
        usuario.setPassword(encryptedPassword);

        usuarioRepositorio.save(usuario);

        UsuarioDTO usuarioDTO = UsuarioMapper.toUsuarioDTO(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioDTO);
    }

    @Override
    public ResponseEntity<UsuarioDTO> getUsuarioById(Long id) {
        if (id == null || id <= 0) {
            throw new idInvalidoException("El id proporcionado no es válido: " + id);
        }

        Usuario usuario = usuarioRepositorio.findById(id)
                .orElseThrow(() -> new elementoNoEncontradoException(id, "Usuario no encontrado con id: "));

        return ResponseEntity.status(HttpStatus.OK).body(UsuarioMapper.toUsuarioDTO(usuario));
    }

    @Override
    public ResponseEntity<Void> deleteUsuario(Long id) {
        if (id == null || id <= 0) {
            throw new idInvalidoException("El id proporcionado no es válido: " + id);
        }

        Usuario usuario = usuarioRepositorio.findById(id)
                .orElseThrow(() -> new elementoNoEncontradoException(id, "Usuario no encontrado con id: "));
        usuarioRepositorio.delete(usuario);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
