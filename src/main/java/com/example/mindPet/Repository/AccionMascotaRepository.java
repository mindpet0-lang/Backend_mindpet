package com.example.mindPet.Repository;

import com.example.mindPet.Model.AccionMascota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccionMascotaRepository extends JpaRepository<AccionMascota, Integer> {
}