package com.example.mindPet.Model; // Ajusta tu paquete

import jakarta.persistence.*;
import com.example.mindPet.Model.Usuario;

@Entity
@Table(name = "likes_publicaciones",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"publicacion_id", "usuario_id"})})
// El uniqueConstraint evita que un usuario le de más de 1 like a la misma publicación
public class LikePublicacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "publicacion_id", nullable = false)
    private Publicacion publicacion;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario; // Reemplaza por tu entidad 'User' si se llama así

    // --- CONSTRUCTORES ---
    public LikePublicacion() {}

    public LikePublicacion(Publicacion publicacion, Usuario usuario) {
        this.publicacion = publicacion;
        this.usuario = usuario;
    }

    // --- GETTERS Y SETTERS ---
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Publicacion getPublicacion() { return publicacion; }
    public void setPublicacion(Publicacion publicacion) { this.publicacion = publicacion; }
    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }
}