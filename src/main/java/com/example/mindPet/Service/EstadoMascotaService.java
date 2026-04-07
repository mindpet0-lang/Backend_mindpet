package com.example.mindPet.Service;

import com.example.mindPet.Model.EstadoMascota;
import com.example.mindPet.Repository.EstadoMascotaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstadoMascotaService {

    private final EstadoMascotaRepository estadoMascotaRepository;

    public EstadoMascotaService(EstadoMascotaRepository estadoMascotaRepository) {
        this.estadoMascotaRepository = estadoMascotaRepository;
    }

    public List<EstadoMascota> obtenerEstadosMascota() {
        return estadoMascotaRepository.findAll();
    }

    public EstadoMascota guardarEstadoMascota(EstadoMascota estadoMascota) {
        return estadoMascotaRepository.save(estadoMascota);
    }

    public EstadoMascota actualizarEstadoMascota(int id, EstadoMascota estadoMascota) {
        estadoMascota.setId(id);
        return estadoMascotaRepository.save(estadoMascota);
    }

    public void eliminarEstadoMascota(int id) {
        estadoMascotaRepository.deleteById(id);
    }
}