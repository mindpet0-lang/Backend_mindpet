package com.example.mindPet.Repository;
import com.example.mindPet.Model.Comentario;
import com.example.mindPet.Model.LikeComentario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ComentarioRepository extends JpaRepository<Comentario, Long> {
    List<Comentario> findByPublicacionIdOrderByIdAsc(Long publicacionId);

}