package com.example.mindPet.Controller;

import com.example.mindPet.Model.Inventario;
import com.example.mindPet.Service.InventarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/tienda")
public class TiendaController {

    @Autowired
    private InventarioService inventarioService;

    // --- PARA LA TIENDA (YA LO TENÍAS) ---
    @PostMapping("/comprar")
    public ResponseEntity<?> realizarCompra(@RequestBody Map<String, Object> payload) {
        try {
            // Conversión segura para evitar el Bad Request
            Long userId = Long.valueOf(payload.get("userId").toString());

            // Usamos Double primero porque el JSON a veces interpreta números como decimales
            // y luego lo pasamos a int.
            int total = Double.valueOf(payload.get("total").toString()).intValue();

            List<Map<String, Object>> items = (List<Map<String, Object>>) payload.get("items");
            String categoria = payload.getOrDefault("categoria", "COMIDA").toString();

            inventarioService.procesarCompra(userId, total, items);

            return ResponseEntity.ok(Map.of("mensaje", "¡Compra exitosa! 🐾"));
        } catch (Exception e) {
            e.printStackTrace(); // Esto hará que AHORA SÍ veas el error en la consola si algo falla
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    // --- PARA LA COCINA (FILTRADO) ---
    @GetMapping("/inventario/{userId}/comida")
    public List<Inventario> verComida(@PathVariable Long userId) {
        // Este método 'obtenerSoloComida' debe estar en tu InventarioService
        return inventarioService.obtenerSoloComida(userId);
    }

    // --- PARA LA COCINA (ALIMENTAR) ---
    @PostMapping("/consumir")
    public ResponseEntity<?> consumirItem(@RequestBody Map<String, Object> payload) {
        try {
            Long userId = Long.valueOf(payload.get("userId").toString());
            String nombre = payload.get("nombre").toString();

            // Este método 'consumir' debe estar en tu InventarioService
            inventarioService.consumir(userId, nombre);

            return ResponseEntity.ok(Map.of("mensaje", "Item consumido correctamente"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al consumir: " + e.getMessage());
        }
    }

    // --- INVENTARIO GENERAL ---
    @GetMapping("/inventario/{userId}")
    public List<Inventario> verInventario(@PathVariable Long userId) {
        return inventarioService.obtenerInventarioUsuario(userId);
    }
}