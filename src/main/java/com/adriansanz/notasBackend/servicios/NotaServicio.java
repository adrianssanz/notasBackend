package com.adriansanz.notasBackend.servicios;

import java.util.List;

import org.springframework.stereotype.Service;

import com.adriansanz.notasBackend.dto.NotaDTO;
import com.adriansanz.notasBackend.entidades.Nota;

@Service
public interface NotaServicio {
    List<NotaDTO> getAllNotas(int page, int size);

    NotaDTO getNotaById(Long id);

    NotaDTO createNota(Nota nota, Long usuarioId);

    NotaDTO updateNota(Long id, Nota nota);

    void deleteNota(Long id);
}
