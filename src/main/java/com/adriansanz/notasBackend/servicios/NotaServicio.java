package com.adriansanz.notasBackend.servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.adriansanz.notasBackend.entidades.Nota;

@Service
public interface NotaServicio {
    List<Nota> getAllNotas();

    Optional<Nota> getNotaById(Long id);

    Nota createNota(Nota nota);

    void deleteNota(Long id);
}