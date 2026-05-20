package com.example.mindPet.Repository;


import com.example.mindPet.Model.Publicacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PublicacionRepository extends JpaRepository<Publicacion, Long> {
    // Trae las publicaciones ordenadas de la más reciente a la más antigua
    List<Publicacion> findAllByOrderByFechaCreacionDesc();
}