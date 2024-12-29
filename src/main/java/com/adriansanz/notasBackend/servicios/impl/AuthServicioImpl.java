package com.adriansanz.notasBackend.servicios.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.adriansanz.notasBackend.entidades.Usuario;
import com.adriansanz.notasBackend.repositorios.UsuarioRepositorio;
import com.adriansanz.notasBackend.servicios.AuthServicio;

@Service
public class AuthServicioImpl implements AuthServicio {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Override
    public boolean loginUsuario(String usuario, String password) {
        Optional<Usuario> usuarioOptional = usuarioRepositorio.findByUsuario(usuario);
        return usuarioOptional
                .filter(u -> new BCryptPasswordEncoder().matches(password, u.getPassword()))
                .isPresent();
    }   

}
