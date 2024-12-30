package com.adriansanz.notasBackend.dto;

public class UsuarioDTO {
    private Long id;
    private String usuario;
    private RolDTO rolDTO;

    public UsuarioDTO(Long id, String usuario, RolDTO rolDTO) {
        this.id = id;
        this.usuario = usuario;
        this.rolDTO = rolDTO;
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

    public RolDTO getRol() {
        return rolDTO;
    }

    public void setRol(RolDTO rolDTO) {
        this.rolDTO = rolDTO;
    }
}