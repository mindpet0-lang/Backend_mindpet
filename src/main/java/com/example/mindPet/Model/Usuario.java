package com.example.mindPet.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    @Column(unique = true, nullable = false)
    private String correo;

    @Column(nullable = false,length = 255)
    private String contrasena;

    private String fechaNacimiento;

    // --- NUEVO CAMPO PARA MINDPET ---
    @Column(name = "monedas", columnDefinition = "int default 0")
    private int monedas = 0;

    @OneToOne
    @JoinColumn(name = "mascota_id")
    private Mascota mascota;

    public Mascota getMascota() {
        return mascota;
    }

    public void setMascota(Mascota mascota) {
        this.mascota = mascota;
    }

    public Usuario() {}

    // Getters y Setters Originales
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }
    public String getContrasena() { return contrasena; }
    public void setContrasena(String contrasena) { this.contrasena = contrasena; }
    public String getFechaNacimiento() { return fechaNacimiento; }
    public void setFechaNacimiento(String fechaNacimiento) { this.fechaNacimiento = fechaNacimiento; }

    // --- GETTER Y SETTER DE MONEDAS ---
    public int getMonedas() { return monedas; }
    public void setMonedas(int monedas) { this.monedas = monedas; }
}
