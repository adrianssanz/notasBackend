package com.adriansanz.notasBackend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.adriansanz.notasBackend.entidades.Nota;
import com.adriansanz.notasBackend.servicios.Nota.NotaServicio;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/notas")
public class NotaController {
    @Autowired
    private NotaServicio notaServicio;

    @GetMapping
    public List<Nota> getAllNotas(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {
        return notaServicio.getAllNotas(page, size);
    }

    @PostMapping
    public Nota createNota(@Valid @RequestBody Nota nota) {
        return notaServicio.createNota(nota);
    }

    @GetMapping("/{id}")
    public Nota getNotaById(@PathVariable Long id) {
        return notaServicio.getNotaById(id);
    }

    @PutMapping("/{id}")
    public Nota updateNota(@PathVariable Long id, @Valid @RequestBody Nota nota){
        return notaServicio.updateNota(id, nota);
    }

    @DeleteMapping("/{id}")
    public void deleteNota(@PathVariable Long id) {
        notaServicio.deleteNota(id);
    }
}
