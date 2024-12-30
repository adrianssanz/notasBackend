package com.adriansanz.notasBackend.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.adriansanz.notasBackend.entidades.Rol;

public interface RolRepositorio extends JpaRepository<Rol, Long> {
    
}
