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
            Long userId = Long.valueOf(payload.get("userId").toString());
            int total = Double.valueOf(payload.get("total").toString()).intValue();
            List<Map<String, Object>> items = (List<Map<String, Object>>) payload.get("items");

            // 1. Obtenemos la categoría que viene de Flutter
            String categoria = payload.getOrDefault("categoria", "COMIDA").toString();

            // 2. IMPORTANTE: Pasa la categoría como cuarto parámetro (vamos a modificar el service abajo)
            inventarioService.procesarCompra(userId, total, items, categoria);

            return ResponseEntity.ok(Map.of("mensaje", "¡Compra exitosa! 🐾"));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    @GetMapping("/inventario/{userId}/comida-completa")
    public ResponseEntity<List<Inventario>> obtenerComidaCompleta(@PathVariable Long userId) {
        // Llamamos al servicio que junta ambas categorías
        List<Inventario> listaUnica = inventarioService.obtenerComidaYBebida(userId);

        // Retornamos la lista única hacia Flutter
        return ResponseEntity.ok(listaUnica);
    }

    // --- PARA LA COCINA (FILTRADO) ---
    @GetMapping("/inventario/{userId}/comida")
    public List<Inventario> verComida(@PathVariable Long userId) {
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

    @GetMapping("/inventario/{userId}/aseo")
    public List<Inventario> verAseo(@PathVariable Long userId) {
        return inventarioService.obtenerSoloAseo(userId); // Crea este método en el service similar al de comida
    }

    // --- INVENTARIO GENERAL ---
    @GetMapping("/inventario/{userId}")
    public List<Inventario> verInventario(@PathVariable Long userId) {
        return inventarioService.obtenerInventarioUsuario(userId);
    }
}