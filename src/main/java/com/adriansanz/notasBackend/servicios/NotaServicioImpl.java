package com.adriansanz.notasBackend.servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adriansanz.notasBackend.entidades.Nota;
import com.adriansanz.notasBackend.repositorios.NotaRepositorio;

@Service
public class NotaServicioImpl implements NotaServicio {
    @Autowired
    private NotaRepositorio notaRepositorio;

    public NotaServicioImpl(NotaRepositorio notaRepositorio) {
        this.notaRepositorio = notaRepositorio;
    }

    @Override
    public List<Nota> getAllNotas() {
        return notaRepositorio.findAll();
    }

    @Override
    public Optional<Nota> getNotaById(Long id) {
        return notaRepositorio.findById(id);
    }

    @Override
    public Nota createNota(Nota nota) {
        return notaRepositorio.save(nota);
    }

    @Override
    public void deleteNota(Long id) {
        notaRepositorio.deleteById(id);
    }
    
}
