package com.adriansanz.notasBackend.repositorios;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.adriansanz.notasBackend.entidades.Nota;
import com.adriansanz.notasBackend.entidades.Usuario;

public interface NotaRepositorio extends JpaRepository<Nota, Long> {
    Page<Nota> findByUsuario(Usuario usuario, Pageable pageable);
}
