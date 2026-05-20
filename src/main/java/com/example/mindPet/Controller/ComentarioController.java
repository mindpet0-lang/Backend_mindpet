package com.example.mindPet.Controller;

import com.example.mindPet.Model.*;
import com.example.mindPet.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/comentarios")
@CrossOrigin(origins = "*")
public class ComentarioController {

    @Autowired private ComentarioRepository comentarioRepository;
    @Autowired private LikeComentarioRepository likeComentarioRepository;
    @Autowired private PublicacionRepository publicacionRepository;

    @GetMapping("/publicacion/{pubId}")
    public List<Comentario> listarPorPublicacion(@PathVariable Long pubId, @RequestParam Long usuarioId) {
        List<Comentario> comentarios = comentarioRepository.findByPublicacionIdOrderByIdAsc(pubId);

        comentarios.forEach(c -> {
            c.setTotalLikes(likeComentarioRepository.countByComentarioId(c.getId()));
            c.setLeDioLike(likeComentarioRepository.existsByComentarioIdAndUsuarioId(c.getId(), usuarioId));
        });
        return comentarios;
    }

    @PostMapping
    public Comentario crearComentario(@RequestBody Comentario comentario) {
        Usuario u = comentario.getUsuario();
        // Inyectamos un cascarón para guardar la relación relacional
        return comentarioRepository.save(comentario);
    }

    @PostMapping("/{id}/like")
    public ResponseEntity<Long> alternarLike(@PathVariable Long id, @RequestParam Long usuarioId) {
        return comentarioRepository.findById(id).map(comentario -> {
            var exist = likeComentarioRepository.findByComentarioIdAndUsuarioId(id, usuarioId);
            if (exist.isPresent()) {
                likeComentarioRepository.delete(exist.get());
            } else {
                Usuario u = new Usuario(); u.setId(usuarioId);
                likeComentarioRepository.save(new LikeComentario(comentario, u));
            }
            return ResponseEntity.ok(likeComentarioRepository.countByComentarioId(id));
        }).orElse(ResponseEntity.notFound().build());
    }
}