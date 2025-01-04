package com.adriansanz.notasBackend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.adriansanz.notasBackend.dto.NotaDTO;
import com.adriansanz.notasBackend.entidades.Nota;
import com.adriansanz.notasBackend.servicios.NotaServicio;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/notas")
public class NotaController {
    @Autowired
    private NotaServicio notaServicio;

    @GetMapping
    public ResponseEntity<List<NotaDTO>> getAllNotas(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            HttpSession session) {
        return notaServicio.getAllNotas(page, size, session);
    }

    @PostMapping("/{usuarioId}")
    public ResponseEntity<NotaDTO> createNota(@PathVariable Long usuarioId, @Valid @RequestBody Nota nota) {
        return notaServicio.createNota(nota, usuarioId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<NotaDTO> getNotaById(@PathVariable Long id) {
        return notaServicio.getNotaById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<NotaDTO> updateNota(@PathVariable Long id, @Valid @RequestBody Nota nota){
        return notaServicio.updateNota(id, nota);
    }

    @PutMapping("/{id}/estado")
    public ResponseEntity<NotaDTO> updateEstadoNota(@PathVariable Long id){
        return notaServicio.updateEstadoNota(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNota(@PathVariable Long id) {
        notaServicio.deleteNota(id);
        return ResponseEntity.noContent().build();
    }
}
