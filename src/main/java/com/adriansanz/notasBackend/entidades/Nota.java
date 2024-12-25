package com.adriansanz.notasBackend.entidades;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data 
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "notas")
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

    @CreationTimestamp
    @Column(name = "fecha_creacion", updatable = false)
    private LocalDateTime fechaCreacion;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;
}