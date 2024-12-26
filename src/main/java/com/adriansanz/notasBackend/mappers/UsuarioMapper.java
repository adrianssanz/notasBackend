package com.adriansanz.notasBackend.mappers;

import java.util.List;
import java.util.stream.Collectors;

import com.adriansanz.notasBackend.dto.UsuarioDTO;
import com.adriansanz.notasBackend.entidades.Usuario;

public class UsuarioMapper {
    public static UsuarioDTO toUsuarioDTO(Usuario usuario) {
        if (usuario == null) {
            return null;
        }
        return new UsuarioDTO(
                usuario.getId(),
                usuario.getUsuario()
        );
    }

    public static List<UsuarioDTO> toUsuarioDTOList(List<Usuario> usuarios) {
        if (usuarios == null) {
            return null;
        }
        return usuarios.stream()
                .map(UsuarioMapper::toUsuarioDTO)
                .collect(Collectors.toList());
    }
}
