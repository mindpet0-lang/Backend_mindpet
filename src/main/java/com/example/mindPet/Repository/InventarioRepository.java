package com.example.mindPet.Repository;

import com.example.mindPet.Model.Inventario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface InventarioRepository extends JpaRepository<Inventario, Long> {
    List<Inventario> findByUserIdAndCategoria(Long userId, String categoria);



    Optional<Inventario> findByUserIdAndNombre(Long userId, String nombre);

    List<Inventario> findByUserIdAndCategoriaIn(Long userId, List<String> categorias);

    List<Inventario> findByUserId(Long userId);
}