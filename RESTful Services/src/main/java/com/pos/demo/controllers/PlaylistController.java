package com.pos.demo.controllers;

import com.pos.demo.models.Playlist;
import com.pos.demo.models.Song;
import com.pos.demo.repositories.PlaylistRepository;
import com.pos.demo.repositories.SongRepository;
import com.pos.demo.services.PlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/playlists")
public class PlaylistController {
    @Autowired
    private PlaylistService playlistService;
    @Autowired
    private SongRepository songRepository;
    @Autowired
    private PlaylistRepository playlistRepository;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Playlist> findAll() {
        return playlistService.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Playlist findById(@PathVariable int id) {
        return playlistService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody Playlist playlist) {
        playlistService.create(playlist);
    }

    @PostMapping("/songs")
    @ResponseStatus(HttpStatus.CREATED)
    public void addSong(@RequestParam("playlistId") int playlistId, @RequestParam("songId") int songId) {
        Optional<Song> song = songRepository.findById(songId);
        Optional<Playlist> playlist = playlistRepository.findById(playlistId);
        if (song.isEmpty() || playlist.isEmpty())
            return;

        playlistService.addSongToPlaylist(playlistId, song.get());
    }

    @DeleteMapping("/songs")
    @ResponseStatus(HttpStatus.CREATED)
    public void removeSong(@RequestParam("playlistId") int playlistId, @RequestParam("songId") int songId) {
        Optional<Song> song = songRepository.findById(songId);
        if (song.isEmpty())
            return;

        playlistService.removeSongFromPlaylist(playlistId, song.get());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable int id) {
       playlistService.delete(id);
    }

    @PatchMapping
    @ResponseStatus(HttpStatus.OK)
    public void update(@RequestBody Playlist playlist) {
        playlistService.update(playlist);
    }
}