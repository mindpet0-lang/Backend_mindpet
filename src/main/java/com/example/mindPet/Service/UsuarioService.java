package com.example.mindPet.Service;

import com.example.mindPet.Model.Usuario;
import com.example.mindPet.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public List<Usuario> obtenerUsuarios() {
        return usuarioRepository.findAll();
    }

    public Usuario guardarUsuario(Usuario usuario) {
        if (usuarioRepository.findByCorreo(usuario.getCorreo()).isPresent()) {
            throw new RuntimeException("El correo ya está registrado");
        }
        usuario.setContrasena(passwordEncoder.encode(usuario.getContrasena()));
        return usuarioRepository.save(usuario);
    }

    // Lógica de Login movida al Service
    public Map<String, Object> autenticar(String correo, String contrasena) {
        Usuario user = usuarioRepository.findByCorreo(correo)
                .orElseThrow(() -> new RuntimeException("Correo no encontrado"));

        if (!passwordEncoder.matches(contrasena, user.getContrasena())) {
            throw new RuntimeException("Contraseña incorrecta");
        }

        Map<String, Object> response = new HashMap<>();
        response.put("token", "TOKEN_PROVISIONAL_MINDPET");
        response.put("usuario", user);
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