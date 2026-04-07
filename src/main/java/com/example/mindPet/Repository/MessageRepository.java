package com.example.mindPet.Repository;

import com.example.mindPet.Model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

    // Para saber si mostrar el aviso legal al inicio
    long countByUserId(Long userId);

    // Para cargar el chat solo de este usuario en Flutter
    List<Message> findByUserId(Long userId);
}
