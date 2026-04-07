package com.example.mindPet.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "comida_mascota")
public class ComidaMascota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nombre;
    private int energia;

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getNombre() { return nombre; }

    public void setNombre(String nombre) { this.nombre = nombre; }

    public int getEnergia() { return energia; }

    public void setEnergia(int energia) { this.energia = energia; }
}