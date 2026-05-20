package com.example.mindPet.Controller;


import com.example.mindPet.Model.LikePublicacion;
import com.example.mindPet.Model.Publicacion;
import com.example.mindPet.Model.Usuario;
import com.example.mindPet.Repository.LikePublicacionRepository;
import com.example.mindPet.Repository.PublicacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/publicaciones")
@CrossOrigin(origins = "http://localhost:4200")
public class PublicacionController {

    @Autowired
    private PublicacionRepository publicacionRepository;

    @Autowired
    private LikePublicacionRepository likeRepository;

    // GET: http://localhost:8080/api/publicaciones
    @GetMapping
    public ResponseEntity<List<Publicacion>> listarTodas( @RequestParam Long usuarioIdActual) {
        List<Publicacion> publicaciones = publicacionRepository.findAllByOrderByFechaCreacionDesc();

        publicaciones.forEach(p -> {
            p.setTotalLikes(likeRepository.countByPublicacionId(p.getId()));
            p.setLeDioLike(likeRepository.existsByPublicacionIdAndUsuarioId(p.getId(), usuarioIdActual));
        });

        return ResponseEntity.ok(publicaciones);
    }

    // POST: http://localhost:8080/api/publicaciones
    @PostMapping
    public Publicacion guardar(@RequestBody Publicacion publicacion) {
        return publicacionRepository.save(publicacion);
    }

    // DELETE: http://localhost:8080/api/publicaciones/5 (por ejemplo)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {

        if (publicacionRepository.existsById(id)) {
            publicacionRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {

            return ResponseEntity.noContent().build();
        }
    }

    // PUT: http://localhost:8080/api/publicaciones/5
    @PutMapping("/{id}")
    public ResponseEntity<Publicacion> editar(@PathVariable Long id, @RequestBody Publicacion publicacionDetalles) {
        return publicacionRepository.findById(id).map(publicacionExistente -> {
            // Actualizamos solo el contenido del texto
            publicacionExistente.setContenido(publicacionDetalles.getContenido());

            Publicacion actualizada = publicacionRepository.save(publicacionExistente);
            return ResponseEntity.ok(actualizada);
        }).orElse(ResponseEntity.notFound().build());
    }

    //gestionar likes
    // POST: http://localhost:8080/api/publicaciones/5/like?usuarioId=1
    @PostMapping("/{id}/like")
    public ResponseEntity<Long> alternarLike(@PathVariable Long id, @RequestParam Long usuarioId) {
        return publicacionRepository.findById(id).map(publicacion -> {

            // Buscamos si el usuario ya le dio like a esta publicación
            var likeExistente = likeRepository.findByPublicacionIdAndUsuarioId(id, usuarioId);

            if (likeExistente.isPresent()) {
                // Si ya existía, lo quitamos (Dislike)
                likeRepository.delete(likeExistente.get());
            } else {
                // Si no existía, creamos el Me gusta
                Usuario usuario = new Usuario(); // Instanciamos un cascarón con el ID
                usuario.setId(usuarioId);

                LikePublicacion nuevoLike = new LikePublicacion(publicacion, usuario);
                likeRepository.save(nuevoLike);
            }

            // Devolvemos el conteo actualizado de likes totales de la publicación
            long totalLikes = likeRepository.countByPublicacionId(id);
            return ResponseEntity.ok(totalLikes);

        }).orElse(ResponseEntity.notFound().build());
    }
}
