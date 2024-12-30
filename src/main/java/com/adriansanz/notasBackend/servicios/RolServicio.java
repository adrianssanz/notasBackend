package com.adriansanz.notasBackend.servicios;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.adriansanz.notasBackend.entidades.Rol;

@Service
public interface RolServicio {
    ResponseEntity<List<Rol>> getAllRoles();

    ResponseEntity<Rol> createRol(Rol rol);
}
