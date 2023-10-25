package com.proyecto.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto.modelo.Song;

public interface SongRepository extends JpaRepository<Song, Integer> {
}
