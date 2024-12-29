package com.adriansanz.notasBackend.entidades;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
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

    @NotEmpty(message = "La contraseña no puede estar vacía")
    @Column(name = "password", nullable = false)
    private String password;

    @CreationTimestamp
    @Column(name = "fecha_creacion", updatable = false)
    private LocalDateTime fechaCreacion;
}
