package com.example.mindPet.Controller;

import com.example.mindPet.Model.Foro;
import com.example.mindPet.Repository.ForoRepository;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/foros")
@CrossOrigin(origins = "*")
public class ForoController {

    private final ForoRepository repo;

    public ForoController(ForoRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<Foro> listar() {
        return repo.findAll();
    }

    @PostMapping
    public Foro crear(@RequestBody Foro foro) {
        // Inicializar likes vacíos si no vienen
        if (foro.getLikes() == null) {
            foro.setLikes(new ArrayList<>());
        }
        return repo.save(foro);
    }

    @PutMapping("/{id}")
    public Foro actualizar(@PathVariable int id, @RequestBody Foro foro) {
        Foro existente = repo.findById(id).orElseThrow(() -> new RuntimeException("Foro no encontrado"));
        // Actualizar todos los campos necesarios
        existente.setContent(foro.getContent());
        existente.setAuthor(foro.getAuthor());
        existente.setImage(foro.getImage());
        existente.setLikes(foro.getLikes());
        return repo.save(existente);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable int id) {
        repo.deleteById(id);
    }
}