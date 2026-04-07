package com.example.mindPet.Service;

import com.example.mindPet.Model.MensajeChat;
import com.example.mindPet.Repository.MensajeChatRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MensajeChatService {

    private final MensajeChatRepository repository;

    public MensajeChatService(MensajeChatRepository repository) {
        this.repository = repository;
    }

    public List<MensajeChat> listar() {
        return repository.findAll();
    }

    public MensajeChat guardar(MensajeChat mensaje) {
        return repository.save(mensaje);
    }

    public MensajeChat actualizar(int id, MensajeChat mensaje) {
        mensaje.setId(id);
        return repository.save(mensaje);
    }

    public void eliminar(int id) {
        repository.deleteById(id);
    }
}