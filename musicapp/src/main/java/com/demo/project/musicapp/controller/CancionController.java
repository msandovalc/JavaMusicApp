package com.demo.project.musicapp.controller;

import com.demo.project.musicapp.model.Cancion;
import com.demo.project.musicapp.model.Artista;
import com.demo.project.musicapp.service.CancionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/canciones")
public class CancionController {

    private final CancionService cancionService;

    @Autowired
    public CancionController(CancionService cancionService) {
        this.cancionService = cancionService;
    }

    @PostMapping
    public Cancion crearCancion(@RequestBody Cancion cancion) {
        return cancionService.guardarCancion(cancion);
    }

    @GetMapping("/artista/{id}")
    public List<Cancion> obtenerCancionesPorArtista(@PathVariable Long id) {
        return cancionService.obtenerCancionesPorArtista(id);
    }
}
