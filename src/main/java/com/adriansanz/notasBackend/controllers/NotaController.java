package com.adriansanz.notasBackend.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adriansanz.notasBackend.entidades.Nota;
import com.adriansanz.notasBackend.servicios.NotaServicio;

@RestController
@RequestMapping("/api/notas")
public class NotaController {
    @Autowired
    private NotaServicio notaServicio;

    @GetMapping
    public List<Nota> getAllNotas(){
        return notaServicio.getAllNotas();
    }

    @PostMapping
    public Nota createNota(@RequestBody Nota nota){
        return notaServicio.createNota(nota);
    }

    @GetMapping("/{id}")
    public Nota getNotaById(@PathVariable Long id){
        return notaServicio.getNotaById(id);
    }   

    @DeleteMapping("/{id}")
    public void deleteNota(@PathVariable Long id){
        notaServicio.deleteNota(id);
    }   
}
