package com.adriansanz.notasBackend.repositorios;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.adriansanz.notasBackend.entidades.Usuario;

public interface UsuarioRepositorio extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByUsuario(String usuario);
}
