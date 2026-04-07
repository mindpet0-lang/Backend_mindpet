package com.example.mindPet.Repository;

import com.example.mindPet.Model.Foro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ForoRepository extends JpaRepository<Foro, Integer> {
}