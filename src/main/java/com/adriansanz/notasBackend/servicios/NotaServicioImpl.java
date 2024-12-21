package com.adriansanz.notasBackend.servicios;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.adriansanz.notasBackend.entidades.Nota;
import com.adriansanz.notasBackend.excepciones.notaNoEncontradaException;
import com.adriansanz.notasBackend.repositorios.NotaRepositorio;

@Service
public class NotaServicioImpl implements NotaServicio {
    @Autowired
    private NotaRepositorio notaRepositorio;

    public NotaServicioImpl(NotaRepositorio notaRepositorio) {
        this.notaRepositorio = notaRepositorio;
    }

    @Override
    public List<Nota> getAllNotas(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return notaRepositorio.findAll(pageable).getContent();
    }

    @Override
    public Nota getNotaById(Long id) {
        return notaRepositorio.findById(id)
                .orElseThrow(() -> new notaNoEncontradaException(id));
    }

    @Override
    public Nota createNota(Nota nota) {
        return notaRepositorio.save(nota);
    }

    @Override
    public Nota updateNota(Long id, Nota nota) {
        Nota notaNueva = notaRepositorio.findById(id)
                .orElseThrow(() -> new notaNoEncontradaException(id));
        notaNueva.setTitulo(nota.getTitulo());
        notaNueva.setDescripcion(nota.getDescripcion());
        return notaRepositorio.save(notaNueva);
    }

    @Override
    public void deleteNota(Long id) {
        Nota nota = notaRepositorio.findById(id)
                .orElseThrow(() -> new notaNoEncontradaException(id));
        notaRepositorio.delete(nota);
    }

}
