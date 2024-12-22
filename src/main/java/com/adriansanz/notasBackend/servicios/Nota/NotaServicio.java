package com.adriansanz.notasBackend.servicios.Nota;

import java.util.List;

import org.springframework.stereotype.Service;

import com.adriansanz.notasBackend.entidades.Nota;

@Service
public interface NotaServicio {
    List<Nota> getAllNotas(int page, int size);

    Nota getNotaById(Long id);

    Nota createNota(Nota nota);

    Nota updateNota(Long id, Nota nota);

    void deleteNota(Long id);
}
