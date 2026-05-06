package com.example.mindPet.Repository;

import com.example.mindPet.Model.Mascota;
import com.example.mindPet.Model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MascotaRepository extends JpaRepository<Mascota,Integer> {

    Optional<Mascota> findByDuenioId(Long usuarioId);
}