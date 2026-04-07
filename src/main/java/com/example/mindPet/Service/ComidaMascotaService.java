package com.example.mindPet.Service;

import com.example.mindPet.Model.ComidaMascota;
import com.example.mindPet.Repository.ComidaMascotaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComidaMascotaService {

    private final ComidaMascotaRepository comidaMascotaRepository;

    public ComidaMascotaService(ComidaMascotaRepository comidaMascotaRepository) {
        this.comidaMascotaRepository = comidaMascotaRepository;
    }

    public List<ComidaMascota> obtenerComidasMascota() {
        return comidaMascotaRepository.findAll();
    }

    public ComidaMascota guardarComidaMascota(ComidaMascota comidaMascota) {
        return comidaMascotaRepository.save(comidaMascota);
    }

    public ComidaMascota actualizarComidaMascota(int id, ComidaMascota comidaMascota) {
        comidaMascota.setId(id);
        return comidaMascotaRepository.save(comidaMascota);
    }

    public void eliminarComidaMascota(int id) {
        comidaMascotaRepository.deleteById(id);
    }
}