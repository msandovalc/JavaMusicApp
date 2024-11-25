package com.demo.project.musicapp.service;

import com.demo.project.musicapp.model.Artista;
import com.demo.project.musicapp.model.Cancion;
import com.demo.project.musicapp.repository.ArtistaRepository;
import com.demo.project.musicapp.repository.CancionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CancionService {

    @Autowired
    private CancionRepository cancionRepository;

    @Autowired
    private ArtistaRepository artistaRepository;

    @Autowired
    public CancionService(CancionRepository cancionRepository) {
        this.cancionRepository = cancionRepository;
    }

    public Cancion guardarCancion(Cancion cancion) {
        return cancionRepository.save(cancion);
    }

    public List<Cancion> obtenerCancionesPorArtista(Long artistaId) {
        Artista artista = new Artista();
        artista.setId(artistaId);
        return cancionRepository.findByArtista(artista);
    }

    // Método para obtener canciones de un artista por su nombre
    public List<Cancion> buscarCancionesPorArtista(String nombreArtista) {
        // Buscar el artista por nombre
        Optional<Artista> artistaOpt = artistaRepository.findByNombre(nombreArtista);

        if (artistaOpt.isPresent()) {
            // Si se encuentra el artista, buscar las canciones asociadas
            Artista artista = artistaOpt.get();
            return cancionRepository.findByArtista(artista);
        } else {
            // Si no se encuentra el artista, devolver una lista vacía o manejar el error
            return List.of();
        }
    }
}
