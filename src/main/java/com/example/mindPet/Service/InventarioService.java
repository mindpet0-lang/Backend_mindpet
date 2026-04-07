package com.example.mindPet.Service;

import com.example.mindPet.Model.Inventario;
import com.example.mindPet.Repository.InventarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventarioService {

    private final InventarioRepository repository;

    public InventarioService(InventarioRepository repository) {
        this.repository = repository;
    }

    public List<Inventario> listar() {
        return repository.findAll();
    }

    public Inventario guardar(Inventario inventario) {
        return repository.save(inventario);
    }

    public Inventario actualizar(int id, Inventario inventario) {
        inventario.setId(id);
        return repository.save(inventario);
    }

    public void eliminar(int id) {
        repository.deleteById(id);
    }
}