package com.example.mindPet.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "diarios")
public class Diario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @Lob
    @Column(columnDefinition = "LONGTEXT")
    private String contenido;

    private String titulo;

    private String emocion; // para obtener emocion

    private int usuarioId; // para obtener el usuario

}