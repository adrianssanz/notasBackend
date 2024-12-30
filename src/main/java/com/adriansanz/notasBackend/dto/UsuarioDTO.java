package com.adriansanz.notasBackend.dto;

import com.adriansanz.notasBackend.entidades.Rol;

public class UsuarioDTO {
    private Long id;
    private String usuario;
    private Rol rol;

    public UsuarioDTO(Long id, String usuario, Rol rol) {
        this.id = id;
        this.usuario = usuario;
        this.rol = rol;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }
}