package com.adriansanz.notasBackend.mappers;

import java.util.List;
import java.util.stream.Collectors;

import com.adriansanz.notasBackend.dto.NotaDTO;
import com.adriansanz.notasBackend.entidades.Nota;

public class NotaMapper {
    public static NotaDTO toNotaDTO(Nota nota) {
        if (nota == null) {
            return null;
        }
        return new NotaDTO(
                nota.getId(),
                nota.getTitulo(),
                nota.getDescripcion(),
                nota.getFechaCreacion(),
                nota.getEstado(),
                UsuarioMapper.toUsuarioDTO(nota.getUsuario()));
    }

    public static List<NotaDTO> toNotaDTOList(List<Nota> notas) {
        if (notas == null) {
            return null;
        }
        return notas.stream()
                .map(NotaMapper::toNotaDTO)
                .collect(Collectors.toList());
    }
}
