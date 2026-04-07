package com.example.mindPet.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "inventario")
public class Inventario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String objeto;
    private int cantidad;

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getObjeto() { return objeto; }

    public void setObjeto(String objeto) { this.objeto = objeto; }

    public int getCantidad() { return cantidad; }

    public void setCantidad(int cantidad) { this.cantidad = cantidad; }
}