package com.example.mindPet.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String contenido;

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getContenido() { return contenido; }

    public void setContenido(String contenido) { this.contenido = contenido; }
}