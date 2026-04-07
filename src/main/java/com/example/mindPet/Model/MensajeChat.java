package com.example.mindPet.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "mensajes_chat")
public class MensajeChat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String mensaje;

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getMensaje() { return mensaje; }

    public void setMensaje(String mensaje) { this.mensaje = mensaje; }
}