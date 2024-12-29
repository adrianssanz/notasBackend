package com.adriansanz.notasBackend.servicios.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.adriansanz.notasBackend.dto.NotaDTO;
import com.adriansanz.notasBackend.entidades.Nota;
import com.adriansanz.notasBackend.entidades.Usuario;
import com.adriansanz.notasBackend.excepciones.idInvalidoException;
import com.adriansanz.notasBackend.excepciones.elementoNoEncontradoException;
import com.adriansanz.notasBackend.mappers.NotaMapper;
import com.adriansanz.notasBackend.repositorios.NotaRepositorio;
import com.adriansanz.notasBackend.repositorios.UsuarioRepositorio;
import com.adriansanz.notasBackend.servicios.NotaServicio;

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
    public ResponseEntity<List<NotaDTO>> getAllNotas(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        List<Nota> notas = notaRepositorio.findAll(pageable).getContent();
        return ResponseEntity.status(HttpStatus.OK).body(NotaMapper.toNotaDTOList(notas));
    }

    @Override
    public ResponseEntity<NotaDTO> getNotaById(Long id) {
        if (id == null || id <= 0) {
            throw new idInvalidoException("El id proporcionado no es válido: " + id);
        }

        Nota nota = notaRepositorio.findById(id)
                .orElseThrow(() -> new elementoNoEncontradoException(id, "Nota no encontrada con id: "));
        return ResponseEntity.status(HttpStatus.OK).body(NotaMapper.toNotaDTO(nota));
    }

    @Override
    public ResponseEntity<NotaDTO> createNota(Nota nota, Long usuarioId) {
        if (usuarioId == null || usuarioId <= 0) {
            throw new idInvalidoException("El id proporcionado no es válido: " + usuarioId);
        }

        Usuario usuario = usuarioRepositorio.findById(usuarioId)
                .orElseThrow(() -> new elementoNoEncontradoException(usuarioId, "Usuario no encontrado con id: "));
        Nota notaNueva = new Nota();

        notaNueva.setTitulo(nota.getTitulo());
        notaNueva.setDescripcion(nota.getDescripcion());
        notaNueva.setUsuario(usuario);

        notaRepositorio.save(notaNueva);

        return ResponseEntity.status(HttpStatus.CREATED).body(NotaMapper.toNotaDTO(notaNueva));
    }

    @Override
    public ResponseEntity<NotaDTO> updateNota(Long id, Nota nota) {
        if (id == null || id <= 0) {
            throw new idInvalidoException("El id proporcionado no es válido: " + id);
        }

        Nota notaNueva = notaRepositorio.findById(id)
                .orElseThrow(() -> new elementoNoEncontradoException(id, "Nota no encontrada con id: "));
        notaNueva.setTitulo(nota.getTitulo());
        notaNueva.setDescripcion(nota.getDescripcion());
        notaRepositorio.save(notaNueva);
        return ResponseEntity.status(HttpStatus.OK).body(NotaMapper.toNotaDTO(notaNueva));
    }

    @Override
    public ResponseEntity<Void> deleteNota(Long id) {
        if (id == null || id <= 0) {
            throw new idInvalidoException("El id proporcionado no es válido: " + id);
        }

        Nota nota = notaRepositorio.findById(id)
                .orElseThrow(() -> new elementoNoEncontradoException(id, "Nota no encontrada con id: "));
        notaRepositorio.delete(nota);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
