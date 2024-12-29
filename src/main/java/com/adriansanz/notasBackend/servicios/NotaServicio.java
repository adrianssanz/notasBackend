package com.adriansanz.notasBackend.servicios;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.adriansanz.notasBackend.dto.NotaDTO;
import com.adriansanz.notasBackend.entidades.Nota;

@Service
public interface NotaServicio {
    ResponseEntity<List<NotaDTO>> getAllNotas(int page, int size);

    ResponseEntity<NotaDTO> getNotaById(Long id);

    ResponseEntity<NotaDTO> createNota(Nota nota, Long usuarioId);

    ResponseEntity<NotaDTO> updateNota(Long id, Nota nota);

    ResponseEntity<Void> deleteNota(Long id);
}
