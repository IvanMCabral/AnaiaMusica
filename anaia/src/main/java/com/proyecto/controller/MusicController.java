package com.proyecto.controller;



import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.proyecto.modelo.Song;
import com.proyecto.service.SongService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/api")
public class MusicController {
	
  @Autowired private SongService songService;

  private final Set<String> validTokens = new HashSet<>();

  @GetMapping("/music/token")
  public ResponseEntity<String> generateToken(HttpSession session) {
      String token = UUID.randomUUID().toString();
      session.setAttribute("musicToken", token);
      return ResponseEntity.ok(token);
  }
  
  @PostMapping("/music/token")
  public ResponseEntity<Void> storeToken(@RequestBody String token) {
      validTokens.add(token);
      return ResponseEntity.ok().build();
  }


  @GetMapping("/music/{filename:.+}")
  public ResponseEntity<Resource> getMusic(@PathVariable String filename, @RequestHeader("Authorization") String authorizationHeader) {
      String token = authorizationHeader.replace("Bearer ", "");
      if (!validTokens.contains(token)) {
          return ResponseEntity.notFound().build();
      }

      Resource musicFile = new ClassPathResource("static/music/" + filename);
      if (!musicFile.exists()) {
          return ResponseEntity.notFound().build();
      }
      HttpHeaders headers = new HttpHeaders();
      headers.add(HttpHeaders.CONTENT_TYPE, "audio/mpeg");
      headers.add(HttpHeaders.CONTENT_DISPOSITION, "inline");

      return ResponseEntity.ok()
              .headers(headers)
              .body(musicFile);
  }

  
  @GetMapping("/music/list")
  public List<Song> getMusicList() {
      return songService.getAllSongs();
  }


  @PostMapping(value = "/song/register")
  public Song register(@RequestParam("file") MultipartFile file, 
                       @RequestParam("name") String name,
                       @RequestParam("artist") String artist, 
                       @RequestParam("category") String category) throws IOException {
	  
	 
      Song song = new Song();
      song.setName(name);
      song.setArtist(artist);
      song.setCategory(category);

      Path musicPath = Paths.get("src/main/resources/static/music");
      if (!Files.exists(musicPath)) {
          try {
              Files.createDirectories(musicPath);
          } catch (IOException e) {
              e.printStackTrace();
          }
      }
      try {
          Files.copy(file.getInputStream(), musicPath.resolve(file.getOriginalFilename()));
      } catch (IOException e) {
          e.printStackTrace();
      }

      // Guardar la canción utilizando el servicio
      return songService.createSong(song, file);
  }

  
}



