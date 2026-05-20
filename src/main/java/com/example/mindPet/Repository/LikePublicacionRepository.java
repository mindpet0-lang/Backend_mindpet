package com.example.mindPet.Repository;

import com.example.mindPet.Model.LikePublicacion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikePublicacionRepository extends JpaRepository<LikePublicacion, Long> {
    // Cuenta cuántos likes tiene una publicación
    long countByPublicacionId(Long publicacionId);

    // Verifica si un usuario específico ya le dio like a esa publicación
    boolean existsByPublicacionIdAndUsuarioId(Long publicacionId, Long usuarioId);

    // Busca el registro por si el usuario decide quitar el like (Dislike)
    Optional<LikePublicacion> findByPublicacionIdAndUsuarioId(Long publicacionId, Long usuarioId);
}
