package com.example.mindPet.Model;

import jakarta.persistence.*;

@Entity
@Table(name="sesiones")
public class Sesion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String fechaInicio;
    private String fechaFin;

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getFechaInicio() { return fechaInicio; }

    public void setFechaInicio(String fechaInicio) { this.fechaInicio = fechaInicio; }

    public String getFechaFin() { return fechaFin; }

    public void setFechaFin(String fechaFin) { this.fechaFin = fechaFin; }
}