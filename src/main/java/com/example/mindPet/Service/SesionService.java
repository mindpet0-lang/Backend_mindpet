package com.example.mindPet.Service;

import com.example.mindPet.Model.Sesion;
import com.example.mindPet.Repository.SesionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SesionService {

    private final SesionRepository repository;

    public SesionService(SesionRepository repository) {
        this.repository = repository;
    }

    public List<Sesion> listar() {
        return repository.findAll();
    }

    public Sesion guardar(Sesion sesion) {
        return repository.save(sesion);
    }

    public Sesion actualizar(int id, Sesion sesion) {
        sesion.setId(id);
        return repository.save(sesion);
    }

    public void eliminar(int id) {
        repository.deleteById(id);
    }
}