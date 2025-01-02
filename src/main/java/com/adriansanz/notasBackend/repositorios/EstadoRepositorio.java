package com.adriansanz.notasBackend.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.adriansanz.notasBackend.entidades.Estado;

public interface EstadoRepositorio extends JpaRepository<Estado, Long> {
    
}
