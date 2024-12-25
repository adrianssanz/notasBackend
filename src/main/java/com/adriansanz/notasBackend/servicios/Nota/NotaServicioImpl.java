package com.adriansanz.notasBackend.servicios.Nota;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.adriansanz.notasBackend.entidades.Nota;
import com.adriansanz.notasBackend.entidades.Usuario;
import com.adriansanz.notasBackend.excepciones.elementoNoEncontradoException;
import com.adriansanz.notasBackend.repositorios.NotaRepositorio;
import com.adriansanz.notasBackend.repositorios.UsuarioRepositorio;

@Service
public class NotaServicioImpl implements NotaServicio {
    @Autowired
    private NotaRepositorio notaRepositorio;
    private UsuarioRepositorio usuarioRepositorio;

    public NotaServicioImpl(NotaRepositorio notaRepositorio, UsuarioRepositorio usuarioRepositorio) {
        this.notaRepositorio = notaRepositorio;
        this.usuarioRepositorio = usuarioRepositorio;
    }

    @Override
    public List<Nota> getAllNotas(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return notaRepositorio.findAll(pageable).getContent();
    }

    @Override
    public Nota getNotaById(Long id) {
        return notaRepositorio.findById(id)
                .orElseThrow(() -> new elementoNoEncontradoException(id, "Nota no encontrada con id: "));
    }

    @Override
    public Nota createNota(Nota nota, Long usuarioId) {
        Usuario usuario = usuarioRepositorio.findById(usuarioId)
                .orElseThrow(() -> new elementoNoEncontradoException(usuarioId, "Usuario no encontrado con id: "));
        Nota notaNueva = new Nota();

        notaNueva.setTitulo(nota.getTitulo());
        notaNueva.setDescripcion(nota.getDescripcion());
        notaNueva.setUsuario(usuario);

        return notaRepositorio.save(notaNueva);
    }

    @Override
    public Nota updateNota(Long id, Nota nota) {
        Nota notaNueva = notaRepositorio.findById(id)
                .orElseThrow(() -> new elementoNoEncontradoException(id, "Nota no encontrada con id: "));
        notaNueva.setTitulo(nota.getTitulo());
        notaNueva.setDescripcion(nota.getDescripcion());
        return notaRepositorio.save(notaNueva);
    }

    @Override
    public void deleteNota(Long id) {
        Nota nota = notaRepositorio.findById(id)
                .orElseThrow(() -> new elementoNoEncontradoException(id, "Nota no encontrada con id: "));
        notaRepositorio.delete(nota);
    }

}
