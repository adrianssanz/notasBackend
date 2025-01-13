package com.adriansanz.notasBackend.servicios;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.adriansanz.notasBackend.dto.NotaDTO;
import com.adriansanz.notasBackend.entidades.Nota;

import jakarta.servlet.http.HttpSession;

@Service
public interface NotaServicio {
    ResponseEntity<List<NotaDTO>> getAllNotas(int page, int size, HttpSession session);

    ResponseEntity<NotaDTO> getNotaById(Long id, HttpSession session);

    ResponseEntity<NotaDTO> createNota(Nota nota, HttpSession session);

    ResponseEntity<NotaDTO> updateNota(Long id, Nota nota, HttpSession session);

    ResponseEntity<NotaDTO> updateEstadoNota(Long id, HttpSession session);

    ResponseEntity<Void> deleteNotaLogico(Long id, HttpSession session);
}
