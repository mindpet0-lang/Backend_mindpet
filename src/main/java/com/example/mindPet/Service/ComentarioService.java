package com.example.mindPet.Service;

import com.example.mindPet.Model.Comentario;
import com.example.mindPet.Repository.ComentarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComentarioService {

    private final ComentarioRepository repository;

    public ComentarioService(ComentarioRepository repository){
        this.repository = repository;
    }

    public List<Comentario> listar(){
        return repository.findAll();
    }

    public Comentario guardar(Comentario comentario){
        return repository.save(comentario);
    }

    public Comentario actualizar(int id, Comentario comentario){
        comentario.setId(id);
        return repository.save(comentario);
    }

    public void eliminar(int id){
        repository.deleteById(id);
    }
}