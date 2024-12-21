package com.adriansanz.notasBackend.entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Entity
public class Nota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @NotEmpty(message = "El título no puede estar vacío")
    @Size(max = 20, message = "El título no puede tener más de 20 caracteres")
    @Column(name="titulo")
    private String titulo;

    @NotEmpty(message = "La descripción no puede estar vacía")
    @Size(max = 100, message = "La descripción no puede tener más de 10 caracteres")
    @Column(name="descripcion")
    private String descripcion;

    // Constructores, getters y setters
    public Nota() {}

    public Nota(String titulo, String descripcion) {
        this.titulo = titulo;
        this.descripcion = descripcion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}