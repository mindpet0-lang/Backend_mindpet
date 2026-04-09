package com.example.mindPet.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "diarios")
public class Diario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String contenido;
    private String fecha;

    private int usuarioId; // NUEVO para que guarde por usuario

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getContenido() { return contenido; }

    public void setContenido(String contenido) { this.contenido = contenido; }

    public String getFecha() { return fecha; }

    public void setFecha(String fecha) { this.fecha = fecha; }

    public int getUsuarioId() { return usuarioId; }

    public void setUsuarioId(int usuarioId) { this.usuarioId = usuarioId; }
}