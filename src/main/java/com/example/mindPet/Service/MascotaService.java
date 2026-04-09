package com.example.mindPet.Service;

import com.example.mindPet.Model.Mascota;
import com.example.mindPet.Repository.MascotaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MascotaService {

    private final MascotaRepository repository;

    public MascotaService(MascotaRepository repository) {
        this.repository = repository;
    }

    public List<Mascota> listar() {
        return repository.findAll();
    }

    public Mascota guardar(Mascota mascota) {
        return repository.save(mascota);
    }

    public Mascota actualizar(int id, Mascota mascota) {
        mascota.setId(id);
        return repository.save(mascota);
    }

    public void eliminar(int id) {
        repository.deleteById(id);
    }


}