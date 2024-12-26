package com.adriansanz.notasBackend.dto;

public class UsuarioDTO {
    private Long id;
    private String usuario;

    // Constructor
    public UsuarioDTO(Long id, String usuario) {
        this.id = id;
        this.usuario = usuario;
    }

    // Getters y setters
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
}