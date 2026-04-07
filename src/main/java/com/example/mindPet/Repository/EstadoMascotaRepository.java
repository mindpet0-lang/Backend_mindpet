package com.example.mindPet.Repository;

import com.example.mindPet.Model.EstadoMascota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstadoMascotaRepository extends JpaRepository<EstadoMascota, Integer> {
}