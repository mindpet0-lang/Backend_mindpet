package com.example.mindPet.Service;

import com.example.mindPet.Model.Diario;
import com.example.mindPet.Repository.DiarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiarioService {

    private final DiarioRepository diarioRepository;

    public DiarioService(DiarioRepository diarioRepository) {
        this.diarioRepository = diarioRepository;
    }

    public List<Diario> obtenerDiarios() {
        return diarioRepository.findAll();
    }

    public Diario guardarDiario(Diario diario) {
        return diarioRepository.save(diario);
    }

    public Diario actualizarDiario(int id, Diario diario) {
        diario.setId(id);
        return diarioRepository.save(diario);
    }

    public void eliminarDiario(int id) {
        diarioRepository.deleteById(id);
    }

    public List<Diario> obtenerDiariosPorUsuario(int usuarioId) {
        return diarioRepository.findByUsuarioId(usuarioId);
    }
}