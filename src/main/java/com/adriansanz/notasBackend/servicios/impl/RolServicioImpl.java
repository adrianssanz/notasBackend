package com.adriansanz.notasBackend.servicios.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.adriansanz.notasBackend.entidades.Rol;
import com.adriansanz.notasBackend.repositorios.RolRepositorio;
import com.adriansanz.notasBackend.servicios.RolServicio;

@Service
public class RolServicioImpl implements RolServicio {
    @Autowired
    private RolRepositorio rolRepositorio;

    public RolServicioImpl(RolRepositorio rolRepositorio) {
        this.rolRepositorio = rolRepositorio;
    }

    @Override
    public ResponseEntity<List<Rol>> getAllRoles() {
        List<Rol> roles = rolRepositorio.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(roles);
    }

    @Override
    public ResponseEntity<Rol> createRol(Rol rol) {
        
        rolRepositorio.save(rol);

        return ResponseEntity.status(HttpStatus.CREATED).body(rol);
    }
}
