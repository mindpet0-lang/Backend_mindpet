package com.example.mindPet.Service;

import com.example.mindPet.Model.Rol;
import com.example.mindPet.Repository.RolRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RolService {

    private final RolRepository repository;

    public RolService(RolRepository repository) {
        this.repository = repository;
    }

    public List<Rol> listar() {
        return repository.findAll();
    }

    public Rol guardar(Rol rol) {
        return repository.save(rol);
    }

    public Rol actualizar(int id, Rol rol) {
        rol.setId(id);
        return repository.save(rol);
    }

    public void eliminar(int id) {
        repository.deleteById(id);
    }
}