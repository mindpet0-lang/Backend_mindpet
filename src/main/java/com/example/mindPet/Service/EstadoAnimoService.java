package com.example.mindPet.Service;

import com.example.mindPet.Model.EstadoAnimo;
import com.example.mindPet.Repository.EstadoAnimoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstadoAnimoService {

    private final EstadoAnimoRepository estadoAnimoRepository;

    public EstadoAnimoService(EstadoAnimoRepository estadoAnimoRepository) {
        this.estadoAnimoRepository = estadoAnimoRepository;
    }

    public List<EstadoAnimo> obtenerEstados() {
        return estadoAnimoRepository.findAll();
    }

    public EstadoAnimo guardarEstado(EstadoAnimo estado) {
        return estadoAnimoRepository.save(estado);
    }

    public EstadoAnimo actualizarEstado(int id, EstadoAnimo estado) {
        estado.setId(id);
        return estadoAnimoRepository.save(estado);
    }

    public void eliminarEstado(int id) {
        estadoAnimoRepository.deleteById(id);
    }
}