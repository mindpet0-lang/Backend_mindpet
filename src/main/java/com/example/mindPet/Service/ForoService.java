package com.example.mindPet.Service;

import com.example.mindPet.Model.Foro;
import com.example.mindPet.Repository.ForoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ForoService {

    private final ForoRepository repository;

    public ForoService(ForoRepository repository) {
        this.repository = repository;
    }

    public List<Foro> listar() {
        return repository.findAll();
    }

    public Foro guardar(Foro foro) {
        return repository.save(foro);
    }

    public Foro actualizar(int id, Foro foro) {
        foro.setId(id);
        return repository.save(foro);
    }

    public void eliminar(int id) {
        repository.deleteById(id);
    }
}