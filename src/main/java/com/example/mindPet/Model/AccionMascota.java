package com.example.mindPet.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "acciones_mascota")
public class AccionMascota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String accion;
    private int energia;
    private int felicidad;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    public int getEnergia() {
        return energia;
    }

    public void setEnergia(int energia) {
        this.energia = energia;
    }

    public int getFelicidad() {
        return felicidad;
    }

    public void setFelicidad(int felicidad) {
        this.felicidad = felicidad;
    }
}