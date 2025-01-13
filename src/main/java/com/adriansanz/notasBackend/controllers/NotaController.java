package com.adriansanz.notasBackend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@CrossOrigin(origins = "http://localhost:4200")
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
    @PostMapping
    public ResponseEntity<NotaDTO> createNota(@Valid @RequestBody Nota nota, HttpSession session) {
        return notaServicio.createNota(nota, session);
    }

    @GetMapping("/{id}")
    public ResponseEntity<NotaDTO> getNotaById(@PathVariable Long id, HttpSession session) {
        return notaServicio.getNotaById(id, session);
    }

    @PutMapping("/{id}")
    public ResponseEntity<NotaDTO> updateNota(@PathVariable Long id, @Valid @RequestBody Nota nota, HttpSession session){
        return notaServicio.updateNota(id, nota, session);
    }

    @PutMapping("/{id}/estado")
    public ResponseEntity<NotaDTO> updateEstadoNota(@PathVariable Long id, HttpSession session){
        return notaServicio.updateEstadoNota(id, session);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNota(@PathVariable Long id, HttpSession session) {
        notaServicio.deleteNotaLogico(id, session);
        return ResponseEntity.noContent().build();
    }
}
