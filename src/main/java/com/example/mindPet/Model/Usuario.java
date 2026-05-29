package com.example.mindPet.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    @Column(unique = true, nullable = false)
    private String correo;

    @Column(nullable = false, length = 255)
    private String contrasena;

    private String fechaNacimiento;

    @Column(name = "monedas", columnDefinition = "int default 0")
    private int monedas = 0;

    // 🔥 CAMBIO IMPORTANTE (evita error de longitud)
    @Column(columnDefinition = "TEXT")
    private String fotoPerfil;

    private String rol;

}