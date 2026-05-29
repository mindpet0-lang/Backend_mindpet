package com.example.mindPet.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="mascotas")
public class Mascota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nombre;
    private int nivel;
    private int energia;
    private int felicidad;
    private int higiene;
    private int hambre;
    @OneToOne
    @JoinColumn(name = "duenio_id")
    @JsonBackReference
    private Usuario duenio;
    private long lastUpdate;
    private boolean isSleeping;


}