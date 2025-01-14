package com.adriansanz.notasBackend.entidades;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "usuarios")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotEmpty(message = "El usuario no puede estar vacío")
    @Size(max = 10, message = "El usuario no puede tener más de 10 caracteres")
    @Column(name ="usuario", unique = true)
    private String usuario;

    @NotEmpty(message = "El email no puede estar vacío")
    @Email(message = "El email debe ser válido")
    @Column(name="email", unique = true)
    private String email;

    @NotEmpty(message = "La contraseña no puede estar vacía")
    @Column(name = "password", nullable = false)
    private String password;

    @ManyToOne
    @JoinColumn(name = "rol_id", nullable = false)
    private Rol rol;

    @CreationTimestamp
    @Column(name = "fecha_creacion", updatable = false)
    private LocalDateTime fechaCreacion;
}
