package com.example.mindPet.Service;

import com.example.mindPet.Model.AccionMascota;
import com.example.mindPet.Repository.AccionMascotaRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AccionMascotaService {

    private final AccionMascotaRepository repository;

    public AccionMascotaService(AccionMascotaRepository repository){
        this.repository = repository;
    }

    public List<AccionMascota> listar(){
        return repository.findAll();
    }

    public AccionMascota guardar(AccionMascota accion){
        return repository.save(accion);
    }

    public void eliminar(int id){
        repository.deleteById(id);
    }
}