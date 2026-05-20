package com.example.mindPet.Controller;


import com.example.mindPet.Model.Usuario;
import com.example.mindPet.Repository.UsuarioRepository;
import com.example.mindPet.Service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Collections;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "*")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping
    public List<Usuario> listarUsuarios() {
        return usuarioService.obtenerUsuarios();
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Usuario> obtenerUsuarioPorId(@PathVariable Long id) {
        return usuarioRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/register")
    public ResponseEntity<?> guardarUsuario(@RequestBody Usuario usuario) {
        try {
            return ResponseEntity.ok(usuarioService.guardarUsuario(usuario));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Collections.singletonMap("message", e.getMessage()));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Usuario loginData) {
        try {
            Map<String, Object> response = usuarioService.autenticar(
                    loginData.getCorreo(),
                    loginData.getContrasena()
            );
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message", e.getMessage()));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarUsuario(@PathVariable Long id, @RequestBody Usuario usuario){
        try {
            return ResponseEntity.ok(usuarioService.actualizarUsuario(id, usuario));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message", e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarUsuario(@PathVariable Long id) {
        usuarioService.eliminarUsuario(id);
        return ResponseEntity.noContent().build();
    }


    @PostMapping("/{id}/sumar-monedas")
    public ResponseEntity<Integer> sumarMonedas(@PathVariable Long id, @RequestParam int monedas) {
        return usuarioRepository.findById(id).map(u -> {
            u.setMonedas(u.getMonedas() + monedas); // Sumamos 500
            usuarioRepository.save(u);
            return ResponseEntity.ok(u.getMonedas());
        }).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/{id}/gastar-monedas")
    public ResponseEntity<?> gastarMonedas(@PathVariable Long id, @RequestParam int monedas) {
        return usuarioRepository.findById(id).map(usuario -> {
            // VALIDACIÓN CRÍTICA: ¿Tiene suficientes monedas?
            if (usuario.getMonedas() >= monedas) {
                usuario.setMonedas(usuario.getMonedas() - monedas); // Restamos
                usuarioRepository.save(usuario);

                // Devolvemos el nuevo saldo con un estado 200 OK
                return ResponseEntity.ok(usuario.getMonedas());
            } else {
                // Si no le alcanza, respondemos con un 400 Bad Request y un mensaje
                return ResponseEntity.badRequest().body("Monedas insuficientes para realizar la transacción.");
            }
        }).orElse(ResponseEntity.notFound().build()); // 404 si el usuario no existe
    }

}