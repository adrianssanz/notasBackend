package com.adriansanz.notasBackend.dto;

public class UsuarioDTO {
    private Long id;
    private String usuario;
    private String email;
    private RolDTO rolDTO;

    
    public UsuarioDTO(Long id, String usuario, String email, RolDTO rolDTO) {
        this.id = id;
        this.usuario = usuario;
        this.email = email;
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
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public RolDTO getRol() {
        return rolDTO;
    }

    public void setRol(RolDTO rolDTO) {
        this.rolDTO = rolDTO;
    }
}