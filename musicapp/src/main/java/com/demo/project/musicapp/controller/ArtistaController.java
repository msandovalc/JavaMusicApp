package com.demo.project.musicapp.controller;

import com.demo.project.musicapp.model.Artista;
import com.demo.project.musicapp.service.ArtistaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/artistas")
public class ArtistaController {

    private final ArtistaService artistaService;

    @Autowired
    public ArtistaController(ArtistaService artistaService) {
        this.artistaService = artistaService;
    }

    @PostMapping
    public Artista crearArtista(@RequestBody Artista artista) {
        return artistaService.guardarArtista(artista);
    }

    @GetMapping("/{id}")
    public Artista obtenerArtista(@PathVariable Long id) {
        return artistaService.obtenerArtistaPorId(id);
    }

    @GetMapping
    public List<Artista> obtenerTodosLosArtistas() {
        return artistaService.obtenerTodosLosArtistas();
    }
}
