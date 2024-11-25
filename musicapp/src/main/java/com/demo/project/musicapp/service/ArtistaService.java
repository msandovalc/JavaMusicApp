package com.demo.project.musicapp.service;

import com.demo.project.musicapp.model.Artista;
import com.demo.project.musicapp.repository.ArtistaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArtistaService {
    private final ArtistaRepository artistaRepository;

    @Autowired
    public ArtistaService(ArtistaRepository artistaRepository) {
        this.artistaRepository = artistaRepository;
    }

    public Artista guardarArtista(Artista artista) {
        return artistaRepository.save(artista);
    }

    public Artista obtenerArtistaPorId(Long id) {
        Optional<Artista> artista = artistaRepository.findById(id);
        return artista.orElse(null);
    }

    public List<Artista> obtenerTodosLosArtistas() {
        return artistaRepository.findAll();
    }
}
