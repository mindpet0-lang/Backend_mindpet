package com.example.mindPet.Repository;

import com.example.mindPet.Model.ComidaMascota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComidaMascotaRepository extends JpaRepository<ComidaMascota, Integer> {
}