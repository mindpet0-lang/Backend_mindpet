package com.example.mindPet.Repository;

import com.example.mindPet.Model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {


    long countByUserId(Long userId);


    List<Message> findByUserId(Long userId);

    List<Message> findByUserIdOrderByTimestampAsc(Long userId);

}
