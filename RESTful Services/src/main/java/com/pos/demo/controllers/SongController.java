package com.pos.demo.controllers;

import com.pos.demo.models.Artist;
import com.pos.demo.models.Song;
import com.pos.demo.repositories.SongRepository;
import com.pos.demo.services.ArtistService;
import com.pos.demo.services.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/songs")
public class SongController {
    @Autowired
    private SongService songService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Song> findAll() {
        return songService.findAll();
    }
    @PatchMapping
    @ResponseStatus(HttpStatus.OK)
    public void update(@RequestBody Song song) {
        songService.update(song);
    }

    @GetMapping("/{page}/{size}")
    @ResponseStatus(HttpStatus.OK)
    public List<Song> findAll(@PathVariable int page, @PathVariable int size) {
        Page p = songService.findAll(page, size);
        List<Song> songs = p.stream().toList();
        return songs;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Song findById(@PathVariable int id) {
        return songService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody Song song) {
        songService.create(song);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable int id) {
        songService.delete(id);
    }
}
