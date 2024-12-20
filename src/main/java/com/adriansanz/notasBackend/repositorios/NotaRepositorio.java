package com.adriansanz.notasBackend.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.adriansanz.notasBackend.entidades.Nota;

public interface NotaRepositorio extends JpaRepository<Nota, Long> {
    
}
