package com.demo.project.musicapp.repository;

import com.demo.project.musicapp.model.Artista;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ArtistaRepository extends JpaRepository<Artista, Long> {
    Optional<Artista> findByNombre(String nombre);
}
