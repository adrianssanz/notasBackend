package com.adriansanz.notasBackend.mappers;

import com.adriansanz.notasBackend.dto.RolDTO;
import com.adriansanz.notasBackend.entidades.Rol;

public class RolMapper {
    public static RolDTO toRolDTO(Rol rol) {
        if (rol == null) {
            return null;
        }
        return new RolDTO(
                rol.getRol());
    }
}
