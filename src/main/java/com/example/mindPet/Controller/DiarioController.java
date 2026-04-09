package com.example.mindPet.Controller;

import com.example.mindPet.Model.Diario;
import com.example.mindPet.Service.DiarioService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/diarios")
@CrossOrigin(origins = "*")
public class DiarioController {

    private final DiarioService diarioService;

    public DiarioController(DiarioService diarioService) {
        this.diarioService = diarioService;
    }

    @GetMapping("/usuario/{usuarioId}")
    public List<Diario> obtenerPorUsuario(@PathVariable int usuarioId) {
        return diarioService.obtenerPorUsuario(usuarioId);
    }

    @PostMapping
    public Diario guardarDiario(@RequestBody Diario diario) {
        return diarioService.guardarDiario(diario);
    }

    @PutMapping("/{id}")
    public Diario actualizarDiario(@PathVariable int id, @RequestBody Diario diario) {
        return diarioService.actualizarDiario(id, diario);
    }

    @DeleteMapping("/{id}/usuario/{usuarioId}")
    public void eliminarDiario(@PathVariable int id, @PathVariable int usuarioId) {
        diarioService.eliminarDiario(id, usuarioId);
    }
}