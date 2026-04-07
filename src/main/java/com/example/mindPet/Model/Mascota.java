package com.example.mindPet.Model;

import jakarta.persistence.*;

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

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getNombre() { return nombre; }

    public void setNombre(String nombre) { this.nombre = nombre; }

    public int getNivel() { return nivel; }

    public void setNivel(int nivel) { this.nivel = nivel; }

    public int getEnergia() { return energia; }

    public void setEnergia(int energia) { this.energia = energia; }

    public int getFelicidad() { return felicidad; }

    public void setFelicidad(int felicidad) { this.felicidad = felicidad; }
}