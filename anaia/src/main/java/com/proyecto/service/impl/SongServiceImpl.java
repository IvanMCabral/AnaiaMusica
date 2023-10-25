package com.proyecto.service.impl;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.proyecto.modelo.Song;
import com.proyecto.repositorios.SongRepository;
import com.proyecto.service.SongService;

@Service
public class SongServiceImpl implements SongService {

    @Autowired
    private SongRepository songRepository;

    @Override
    public List<Song> getAllSongs() {
        return songRepository.findAll();
    }

    @Override
    public Optional<Song> getSongById(int id) {
        return songRepository.findById(id);
    }

    @Override
    public Song createSong(Song song, MultipartFile file) throws IOException {
        return songRepository.save(song);
    }

    @Override
    public Song updateSong(int id, Song song) {
        song.setId(id);
        return songRepository.save(song);
    }

    @Override
    public void deleteSong(int id) {
        songRepository.deleteById(id);
    }
}
