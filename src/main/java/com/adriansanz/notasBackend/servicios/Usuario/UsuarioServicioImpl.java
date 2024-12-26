package com.adriansanz.notasBackend.servicios.Usuario;

import java.util.List;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.adriansanz.notasBackend.dto.UsuarioDTO;
import com.adriansanz.notasBackend.entidades.Usuario;
import com.adriansanz.notasBackend.excepciones.elementoNoEncontradoException;
import com.adriansanz.notasBackend.excepciones.usuarioDuplicadoException;
import com.adriansanz.notasBackend.mappers.UsuarioMapper;
import com.adriansanz.notasBackend.repositorios.UsuarioRepositorio;

@Service
public class UsuarioServicioImpl implements UsuarioServicio {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Override
    public List<UsuarioDTO> getAllUsuarios(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        List<Usuario> usuarios = usuarioRepositorio.findAll(pageable).getContent();
        return UsuarioMapper.toUsuarioDTOList(usuarios);
    }

    @Override
    public UsuarioDTO createUsuario(Usuario usuario) {
        usuarioRepositorio.findByUsuario(usuario.getUsuario())
                .ifPresent(u -> {
                    throw new usuarioDuplicadoException();
                });

        usuarioRepositorio.save(usuario);

        return UsuarioMapper.toUsuarioDTO(usuario);
    }

    @Override
    public UsuarioDTO getUsuarioById(Long id) {

        Usuario usuario = usuarioRepositorio.findById(id)
                .orElseThrow(() -> new elementoNoEncontradoException(id, "Usuario no encontrado con id: "));

        return UsuarioMapper.toUsuarioDTO(usuario);
    }

    @Override
    public boolean loginUsuario(String usuario, String password) {
        Optional<Usuario> usuarioOptional = usuarioRepositorio.findByUsuario(usuario);
        return usuarioOptional
                .filter(u -> new BCryptPasswordEncoder().matches(password, u.getPassword()))
                .isPresent();
    }

}
