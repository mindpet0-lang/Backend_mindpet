package com.example.mindPet.Repository;

import com.example.mindPet.Model.Diario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiarioRepository extends JpaRepository<Diario, Integer> {
}