package com.demo.project.musicapp.repository;

import com.demo.project.musicapp.model.Artista;
import com.demo.project.musicapp.model.Cancion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CancionRepository extends JpaRepository<Cancion, Long> {
    List<Cancion> findByArtista(Artista artista);
}
