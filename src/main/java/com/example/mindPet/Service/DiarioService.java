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

    public List<Diario> obtenerPorUsuario(int usuarioId) {
        return diarioRepository.findByUsuarioId(usuarioId);
    }

    public Diario guardarDiario(Diario diario) {
        return diarioRepository.save(diario);
    }

    public Diario actualizarDiario(int id, Diario diario) {

        Diario existente = diarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Diario no encontrado"));

        // 🔐 VALIDAR USUARIO
        if (existente.getUsuarioId() != diario.getUsuarioId()) {
            throw new RuntimeException("No tienes permiso para editar este diario");
        }

        existente.setContenido(diario.getContenido());
        existente.setFecha(diario.getFecha());
        existente.setEmocion(diario.getEmocion());

        return diarioRepository.save(existente);
    }


    public void eliminarDiario(int id, int usuarioId) {

        Diario diario = diarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Diario no encontrado"));

        // 🔐 VALIDAR USUARIO
        if (diario.getUsuarioId() != usuarioId) {
            throw new RuntimeException("No tienes permiso para eliminar este diario");
        }

        diarioRepository.deleteById(id);
    }
}