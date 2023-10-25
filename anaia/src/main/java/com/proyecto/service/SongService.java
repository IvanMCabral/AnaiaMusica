package com.proyecto.service;


import com.proyecto.modelo.Song;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface SongService {

    List<Song> getAllSongs();

    Optional<Song> getSongById(int id);

    Song createSong(Song song, MultipartFile file) throws IOException;

    Song updateSong(int id, Song song);

    void deleteSong(int id);
    

}