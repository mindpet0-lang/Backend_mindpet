package com.example.mindPet.Repository;

import com.example.mindPet.Model.Diario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiarioRepository extends JpaRepository<Diario, Integer> {
    List<Diario> findByUsuarioId(int usuarioId);
}