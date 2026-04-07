package com.example.mindPet.Service;

import com.example.mindPet.Model.Chat;
import com.example.mindPet.Repository.ChatRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ChatService {

    private final ChatRepository repository;

    public ChatService(ChatRepository repository){
        this.repository = repository;
    }

    public List<Chat> listar(){
        return repository.findAll();
    }

    public Chat guardar(Chat chat){
        return repository.save(chat);
    }

    public Chat actualizar(int id, Chat chat){
        chat.setId(id);
        return repository.save(chat);
    }

    public void eliminar(int id){
        repository.deleteById(id);
    }
}