package com.pos.demo.controllers;

import com.pos.demo.models.Artist;
import com.pos.demo.models.Song;
import com.pos.demo.repositories.ArtistRepository;
import com.pos.demo.services.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/artists")
public class ArtistController {
    @Autowired
    private ArtistService artistService;
    @Autowired
    private ArtistRepository artistRepository;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Artist> findAll() {
        return artistService.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Artist findById(@PathVariable int id) {
        return artistService.findById(id);
    }

    @GetMapping("/find")
    @ResponseStatus(HttpStatus.OK)
    public List<Artist> findAllArtistsLike(@RequestParam String name, @RequestParam String match) {
        if (match.equals("exact")) {
            return artistService.findAllExact(name);
        }
        return artistService.findAllLike(name);
    }

    @GetMapping("/{id}/songs")
    @ResponseStatus(HttpStatus.OK)
    public List<Song> findAllSongsForArtist(@PathVariable int id) {
        return artistService.findSongsForArtist(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody Artist artist) {
        artistService.create(artist);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable int id) {
       artistService.delete(id);
    }

    @PatchMapping
    @ResponseStatus(HttpStatus.OK)
    public void update(@RequestBody Artist artist) {
        artistService.update(artist);
    }
}