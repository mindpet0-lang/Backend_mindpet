package com.example.mindPet.Service;

import com.example.mindPet.Model.Mascota;
import com.example.mindPet.Model.Usuario;
import com.example.mindPet.Repository.MascotaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class MascotaService {

    @Autowired
    private MascotaRepository repository;

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

    public Mascota obtenerMascotaDelUsuario(Long id) {
        return repository.findByDuenioId(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Mascota no encontrada"));
    }

    @Transactional
    public Mascota actualizarEstados(int id, Mascota datosActualizados) {
        // Buscamos la mascota actual
        Mascota mascotaExistente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Mascota con ID " + id + " no encontrada"));

        // Actualizamos los campos de estado que vienen de Flutter
        mascotaExistente.setEnergia(datosActualizados.getEnergia());
        mascotaExistente.setFelicidad(datosActualizados.getFelicidad());
        mascotaExistente.setHambre(datosActualizados.getHambre());
        mascotaExistente.setHigiene(datosActualizados.getHigiene());
        mascotaExistente.setLastUpdate(datosActualizados.getLastUpdate());

        // Si quieres manejar niveles, podrías calcularlo aquí también
        // mascotaExistente.setNivel(nuevoNivel);

        return repository.save(mascotaExistente);
    }
}