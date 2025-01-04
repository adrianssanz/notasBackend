package com.adriansanz.notasBackend.servicios.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.adriansanz.notasBackend.dto.UsuarioDTO;
import com.adriansanz.notasBackend.entidades.Rol;
import com.adriansanz.notasBackend.entidades.Usuario;
import com.adriansanz.notasBackend.excepciones.UsuarioNoAutenticadoException;
import com.adriansanz.notasBackend.excepciones.usuarioDuplicadoException;
import com.adriansanz.notasBackend.mappers.UsuarioMapper;
import com.adriansanz.notasBackend.repositorios.RolRepositorio;
import com.adriansanz.notasBackend.repositorios.UsuarioRepositorio;
import com.adriansanz.notasBackend.servicios.AuthServicio;

import jakarta.servlet.http.HttpSession;

@Service
public class AuthServicioImpl implements AuthServicio {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;
    private RolRepositorio rolRepositorio;

    public AuthServicioImpl(UsuarioRepositorio usuarioRepositorio, RolRepositorio rolRepositorio) {
        this.usuarioRepositorio = usuarioRepositorio;
        this.rolRepositorio = rolRepositorio;
    }

    @Override
    public boolean loginUsuario(String username, String password, HttpSession session) {
        Optional<Usuario> usuarioOptional = usuarioRepositorio.findByUsuario(username);

        if (usuarioOptional.isPresent() &&
            new BCryptPasswordEncoder().matches(password, usuarioOptional.get().getPassword())) {
            session.setAttribute("usuario", usuarioOptional.get());
            return true;
        }

        return false;
    }

	@Override
	public ResponseEntity<UsuarioDTO> registerUsuario(Usuario usuario) {
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
        
        Rol rol = rolRepositorio.findById(1L).get();
        usuario.setRol(rol);

        usuarioRepositorio.save(usuario);

        UsuarioDTO usuarioDTO = UsuarioMapper.toUsuarioDTO(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioDTO);
	}

    @Override
    public Usuario getSesionUsuario(HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        if (usuario == null) {
            throw new UsuarioNoAutenticadoException("No hay un usuario autenticado en la sesión");
        }
        return usuario;
    }

    @Override
    public void logoutUsuario(HttpSession session) {
        session.invalidate();
    }  
}
