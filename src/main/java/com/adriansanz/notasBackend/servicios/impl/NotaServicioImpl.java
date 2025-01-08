package com.adriansanz.notasBackend.servicios.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.adriansanz.notasBackend.dto.NotaDTO;
import com.adriansanz.notasBackend.entidades.Estado;
import com.adriansanz.notasBackend.entidades.Nota;
import com.adriansanz.notasBackend.entidades.Usuario;
import com.adriansanz.notasBackend.excepciones.idInvalidoException;
import com.adriansanz.notasBackend.excepciones.noAutorizadoException;
import com.adriansanz.notasBackend.excepciones.elementoNoEncontradoException;
import com.adriansanz.notasBackend.mappers.NotaMapper;
import com.adriansanz.notasBackend.repositorios.EstadoRepositorio;
import com.adriansanz.notasBackend.repositorios.NotaRepositorio;
import com.adriansanz.notasBackend.servicios.AuthServicio;
import com.adriansanz.notasBackend.servicios.NotaServicio;

import jakarta.servlet.http.HttpSession;

@Service
public class NotaServicioImpl implements NotaServicio {
    @Autowired
    private NotaRepositorio notaRepositorio;
    private AuthServicio authServicio;
    private EstadoRepositorio estadoRepositorio;

    public NotaServicioImpl(NotaRepositorio notaRepositorio, AuthServicio authServicio,
            EstadoRepositorio estadoRepositorio) {
        this.notaRepositorio = notaRepositorio;
        this.estadoRepositorio = estadoRepositorio;
        this.authServicio = authServicio;
    }

    //Metodo para validar que la nota con la que se trabaja es del usuario loggeado
    public void validarNotaUsuario(HttpSession session, Nota nota) {
        Usuario usuario = authServicio.getSesionUsuario(session);
        if (!usuario.getId().equals(nota.getUsuario().getId()) && usuario.getRol().getId() == 2) {
            throw new noAutorizadoException("No tienes permisos para realizar esta acción.");
        }
    }

    //GET ALL
    @Override
    public ResponseEntity<List<NotaDTO>> getAllNotas(int page, int size, HttpSession session) {
        Usuario usuario = authServicio.getSesionUsuario(session);
        Pageable pageable = PageRequest.of(page, size);
        List<Nota> notas;
        if (usuario.getRol().getId() != 2) {
            notas = notaRepositorio.findAll(pageable).getContent();
        } else {
            notas = notaRepositorio.findByUsuario(usuario, pageable).getContent();
        }

        return ResponseEntity.status(HttpStatus.OK).body(NotaMapper.toNotaDTOList(notas));
    }

    //GET BY ID
    @Override
    public ResponseEntity<NotaDTO> getNotaById(Long id, HttpSession session) {
        if (id == null || id <= 0) {
            throw new idInvalidoException("El id proporcionado no es válido: " + id);
        }

        Nota nota = notaRepositorio.findById(id)
                .orElseThrow(() -> new elementoNoEncontradoException(id, "Nota no encontrada con id: "));

        validarNotaUsuario(session, nota);

        return ResponseEntity.status(HttpStatus.OK).body(NotaMapper.toNotaDTO(nota));
    }

    //CREATE 
    @Override
    public ResponseEntity<NotaDTO> createNota(Nota nota, HttpSession session) {
        Usuario usuario = authServicio.getSesionUsuario(session);
        Nota notaNueva = new Nota();

        Estado estado = estadoRepositorio.findById(1L)
                .orElseThrow(() -> new elementoNoEncontradoException(1L, "Estado no encontrado con id: "));

        notaNueva.setTitulo(nota.getTitulo());
        notaNueva.setDescripcion(nota.getDescripcion());
        notaNueva.setUsuario(usuario);
        notaNueva.setEstado(estado);

        notaRepositorio.save(notaNueva);

        return ResponseEntity.status(HttpStatus.CREATED).body(NotaMapper.toNotaDTO(notaNueva));
    }

    //UPDATE
    @Override
    public ResponseEntity<NotaDTO> updateNota(Long id, Nota nota, HttpSession session) {
        if (id == null || id <= 0) {
            throw new idInvalidoException("El id proporcionado no es válido: " + id);
        }

        Nota notaNueva = notaRepositorio.findById(id)
        .orElseThrow(() -> new elementoNoEncontradoException(id, "Nota no encontrada con id: "));

        validarNotaUsuario(session, notaNueva);

        notaNueva.setTitulo(nota.getTitulo());
        notaNueva.setDescripcion(nota.getDescripcion());
        notaRepositorio.save(notaNueva);
        return ResponseEntity.status(HttpStatus.OK).body(NotaMapper.toNotaDTO(notaNueva));
    }

    //DELETE
    @Override
    public ResponseEntity<Void> deleteNota(Long id, HttpSession session) {
        if (id == null || id <= 0) {
            throw new idInvalidoException("El id proporcionado no es válido: " + id);
        }

        Nota nota = notaRepositorio.findById(id)
                .orElseThrow(() -> new elementoNoEncontradoException(id, "Nota no encontrada con id: "));

        validarNotaUsuario(session, nota);

        notaRepositorio.delete(nota);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    //UPDATE ESTADO
    @Override
    public ResponseEntity<NotaDTO> updateEstadoNota(Long id, HttpSession session) {
        if (id == null || id <= 0) {
            throw new idInvalidoException("El id proporcionado no es válido: " + id);
        }

        Nota nota = notaRepositorio.findById(id)
                .orElseThrow(() -> new elementoNoEncontradoException(id, "Nota no encontrada con id: "));

        if (nota.getEstado().getId() == 1) {
            Estado estado = estadoRepositorio.findById(2L)
                    .orElseThrow(() -> new elementoNoEncontradoException(2L, "Estado no encontrado con id: "));
            nota.setEstado(estado);
        } else {
            Estado estado = estadoRepositorio.findById(1L)
                    .orElseThrow(() -> new elementoNoEncontradoException(1L, "Estado no encontrado con id: "));
            nota.setEstado(estado);
        }

        notaRepositorio.save(nota);
        return ResponseEntity.status(HttpStatus.CREATED).body(NotaMapper.toNotaDTO(nota));
    }

}
