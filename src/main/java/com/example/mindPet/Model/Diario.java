package com.example.mindPet.Model;

import jakarta.persistence.*;

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


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getEmocion() { // Guarda la emocion
        return emocion;
    }

    public void setEmocion(String emocion) { // Guarda la emocion
        this.emocion = emocion;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }
}