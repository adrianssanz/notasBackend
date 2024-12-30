package com.adriansanz.notasBackend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adriansanz.notasBackend.entidades.Rol;
import com.adriansanz.notasBackend.servicios.RolServicio;

@RestController
@RequestMapping("/api/roles")
public class RolController {
    @Autowired
    private RolServicio rolServicio;

    @GetMapping
    public ResponseEntity<List<Rol>> getRoles(@RequestBody Rol rol) {
        return rolServicio.getAllRoles();
    }

    @PostMapping
    public ResponseEntity<Rol> createRol(@RequestBody Rol rol) {
        return rolServicio.createRol(rol);
    }
}
