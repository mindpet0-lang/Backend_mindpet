package com.example.mindPet.Service;

import com.example.mindPet.Model.Mascota;
import com.example.mindPet.Model.Usuario;
import com.example.mindPet.Repository.MascotaRepository;
import com.example.mindPet.Repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.*;
import java.util.*;

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

    @Transactional
    public Usuario guardarUsuario(Usuario usuario) {

        if (usuarioRepository.findByCorreo(usuario.getCorreo()).isPresent()) {
            throw new RuntimeException("El correo ya está registrado");
        }

        usuario.setContrasena(passwordEncoder.encode(usuario.getContrasena()));

        Mascota nuevaMascota = new Mascota();
        nuevaMascota.setNombre("Mind pet");
        nuevaMascota.setDuenio(usuario);
        nuevaMascota.setEnergia(80);
        nuevaMascota.setFelicidad(80);
        nuevaMascota.setLastUpdate(System.currentTimeMillis());

        Usuario usuarioGuardado = usuarioRepository.save(usuario);
        mascotaRepository.save(nuevaMascota);

        return usuarioGuardado;
    }

    public Map<String, Object> autenticar(String correo, String contrasena) {

        Usuario usuario = usuarioRepository.findByCorreo(correo)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if (!passwordEncoder.matches(contrasena, usuario.getContrasena())) {
            throw new RuntimeException("Contraseña incorrecta");
        }

        String token = jwtService.generarToken(usuario.getCorreo());

        Map<String, Object> response = new HashMap<>();
        response.put("token", token);
        response.put("id", usuario.getId());
        response.put("nombre", usuario.getNombre());
        response.put("correo", usuario.getCorreo());
        response.put("fotoPerfil", usuario.getFotoPerfil());

        return response;
    }

    // 🔥 ACTUALIZAR PERFIL LIMPIO
    public Usuario actualizarPerfil(Long id, Map<String, String> datos) {

        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if (datos.get("nombre") != null) {
            usuario.setNombre(datos.get("nombre"));
        }

        return usuarioRepository.save(usuario);
    }

    public void cambiarPassword(Long id, String actual, String nueva) {

        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if (!passwordEncoder.matches(actual, usuario.getContrasena())) {
            throw new RuntimeException("Contraseña actual incorrecta");
        }

        usuario.setContrasena(passwordEncoder.encode(nueva));
        usuarioRepository.save(usuario);
    }

    public void eliminarUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }

    // 🔥 SUBIR FOTO
    public String guardarFoto(Long id, MultipartFile file) throws Exception {

        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        String carpeta = "uploads/";
        String nombreArchivo = System.currentTimeMillis() + "_" + file.getOriginalFilename();

        Path ruta = Paths.get(carpeta + nombreArchivo);

        Files.createDirectories(ruta.getParent());
        Files.write(ruta, file.getBytes());

        String url = "https://backendmindpet-production.up.railway.app/uploads/" + nombreArchivo;

        usuario.setFotoPerfil(url);
        usuarioRepository.save(usuario);

        return url;
    }
}