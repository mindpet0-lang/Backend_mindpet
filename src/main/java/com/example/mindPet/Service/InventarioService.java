package com.example.mindPet.Service;

import com.example.mindPet.Model.Inventario;
import com.example.mindPet.Model.Usuario;
import com.example.mindPet.Repository.InventarioRepository;
import com.example.mindPet.Repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class InventarioService {

    @Autowired
    private InventarioRepository inventarioRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Transactional
// Agregamos el parámetro String categoriaGlobal al final
    public void procesarCompra(Long userId, int total, List<Map<String, Object>> items, String categoriaGlobal) {
        Usuario usuario = usuarioRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if (usuario.getMonedas() < total) {
            throw new RuntimeException("Monedas insuficientes");
        }

        usuario.setMonedas(usuario.getMonedas() - total);
        usuarioRepository.save(usuario);

        for (Map<String, Object> itemData : items) {
            String nombre = itemData.get("nombre").toString();
            String imagen = itemData.get("imagen").toString();
            int cantidadAComprar = Double.valueOf(itemData.get("cantidad").toString()).intValue();

            Optional<Inventario> existente = inventarioRepository.findByUserIdAndNombre(userId, nombre);

            if (existente.isPresent()) {
                Inventario inv = existente.get();
                inv.setCantidad(inv.getCantidad() + cantidadAComprar);
                // Aseguramos que mantenga la categoría global por si acaso
                inv.setCategoria(categoriaGlobal);
                inventarioRepository.save(inv);
            } else {
                Inventario nuevo = new Inventario();
                nuevo.setUserId(userId);
                nuevo.setNombre(nombre);
                nuevo.setImagen(imagen);
                // USAMOS LA CATEGORÍA QUE VIENE DEL CONTROLLER
                nuevo.setCategoria(categoriaGlobal);
                nuevo.setCantidad(cantidadAComprar);
                inventarioRepository.save(nuevo);
            }
        }
    }
    public void consumir(Long userId, String nombre) {
        Optional<Inventario> itemOpt = inventarioRepository.findByUserIdAndNombre(userId, nombre);

        if (itemOpt.isPresent()) {
            Inventario item = itemOpt.get();
            if (item.getCantidad() > 1) {
                item.setCantidad(item.getCantidad() - 1);
                inventarioRepository.save(item);
            } else {
                inventarioRepository.delete(item);
            }
        } else {
            throw new RuntimeException("No tienes ese item en el inventario");
        }
    }



    public List<Inventario> obtenerInventarioUsuario(Long userId) {
        return inventarioRepository.findByUserId(userId);
    }

    public List<Inventario> obtenerSoloComida(Long userId) {
        return inventarioRepository.findByUserIdAndCategoria(userId, "COMIDA");
    }

    public List<Inventario> Bebida(Long userId) {
        return inventarioRepository.findByUserIdAndCategoria(userId, "COMIDA");

    }

    public List<Inventario> obtenerComidaYBebida(Long userId) {
        // Creamos la lista con las dos categorías que quieres juntar
        List<String> categorias = List.of("COMIDA", "BEBIDA");

        // El repositorio hace un "SELECT * WHERE categoria IN ('COMIDA', 'BEBIDA')"
        // y te devuelve una SOLA lista con todo junto.
        return inventarioRepository.findByUserIdAndCategoriaIn(userId, categorias);
    }

    public List<Inventario> obtenerSoloAseo(Long userId) {
        return inventarioRepository.findByUserIdAndCategoria(userId, "ASEO");
    }

}