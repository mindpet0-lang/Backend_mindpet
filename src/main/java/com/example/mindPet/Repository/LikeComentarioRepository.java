package com.example.mindPet.Repository;
import com.example.mindPet.Model.LikeComentario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LikeComentarioRepository extends JpaRepository<LikeComentario, Long> {
    long countByComentarioId(Long comentarioId);
    List<LikeComentario> findByComentarioId(Long comentarioId);
    boolean existsByComentarioIdAndUsuarioId(Long comentarioId, Long usuarioId);
    Optional<LikeComentario> findByComentarioIdAndUsuarioId(Long comentarioId, Long usuarioId);}
