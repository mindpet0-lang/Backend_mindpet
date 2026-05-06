package com.example.mindPet.Service;

import com.example.mindPet.Model.Mascota;
import com.example.mindPet.Model.Usuario;
import com.example.mindPet.Repository.MascotaRepository;
import com.example.mindPet.Repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private MascotaRepository mascotaRepository;



    public List<Usuario> obtenerUsuarios() {
        return usuarioRepository.findAll();
    }

    @Transactional// IMPORTANTE: Si algo falla, no se crea ni el usuario ni la mascota
    public Usuario guardarUsuario(Usuario usuario) {
        // 1. Validaciones previas
        if (usuarioRepository.findByCorreo(usuario.getCorreo()).isPresent()) {
            throw new RuntimeException("El correo ya está registrado");
        }


        usuario.setContrasena(passwordEncoder.encode(usuario.getContrasena()));


        Mascota nuevaMascota = new Mascota();
        nuevaMascota.setNombre("Mind pet");
        nuevaMascota.setDuenio(usuario);
        nuevaMascota.setEnergia(80); // Valores iniciales
        nuevaMascota.setFelicidad(80);
        nuevaMascota.setLastUpdate(System.currentTimeMillis());


        Usuario usuarioGuardado = usuarioRepository.save(usuario);


        mascotaRepository.save(nuevaMascota);

        return usuarioGuardado;
    }



    public Map<String, Object> autenticar(String correo, String contrasena) {
        Usuario usuario = usuarioRepository.findByCorreo(correo)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));


        boolean coincide = passwordEncoder.matches(contrasena, usuario.getContrasena());


        if (!coincide) {
            throw new RuntimeException("Contraseña incorrecta");
        }

        String token = jwtService.generarToken(usuario.getCorreo());
        System.out.println(token);

        Map<String, Object> response = new HashMap<>();
        response.put("token", token);
        response.put("id", usuario.getId());
        response.put("nombre", usuario.getNombre());
        response.put("correo", usuario.getCorreo());

        return response;
    }

    public Usuario actualizarUsuario(Long id, Usuario detallesUsuario) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        usuario.setNombre(detallesUsuario.getNombre());
        usuario.setCorreo(detallesUsuario.getCorreo());

        if (detallesUsuario.getContrasena() != null && !detallesUsuario.getContrasena().isEmpty()) {
            usuario.setContrasena(passwordEncoder.encode(detallesUsuario.getContrasena()));
        }

        return usuarioRepository.save(usuario);
    }

    public void eliminarUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }
}