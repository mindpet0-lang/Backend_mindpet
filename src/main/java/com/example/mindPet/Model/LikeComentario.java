package com.example.mindPet.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "likes_comentarios",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"comentario_id", "usuario_id"})})
public class LikeComentario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "comentario_id", nullable = false)
    private Comentario comentario;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    public LikeComentario() {}
    public LikeComentario(Comentario comentario, Usuario usuario) {
        this.comentario = comentario;
        this.usuario = usuario;
    }
    // Getters y setters sencillos...
    public Long getId() { return id; }
    public Comentario getComentario() { return comentario; }
    public void setComentario(Comentario comentario) { this.comentario = comentario; }
    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }
}