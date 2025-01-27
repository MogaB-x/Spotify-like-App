package com.pos.demo.services;

import com.pos.demo.models.Artist;
import com.pos.demo.models.Song;
import com.pos.demo.repositories.ArtistRepository;
import com.pos.demo.repositories.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.util.List;
import java.util.Optional;

@Service
public class ArtistService {
    @Autowired
    private ArtistRepository artistRepository;
    @Autowired
    private SongRepository songRepository;

    public void create(Artist artist) {
        artistRepository.save(artist);
    }

    public List<Artist> findAll() {
        return artistRepository.findAll();
    }

    public List<Artist> findAllLike(String name) {
        return artistRepository.findByNameContaining(name);
    }

    public List<Artist> findAllExact(String name) {
        return artistRepository.findAllByName(name);
    }

    public void delete(int id) {
        artistRepository.deleteById(id);
    }

    public Artist findById(int id) {
        Optional<Artist> artist = artistRepository.findById(id);
        if (artist.isPresent())
            return artist.get();
        return null;
    }

    public List<Song> findSongsForArtist(int id) {
        List<Song> songs = songRepository.findByParent(id);
        return songs;
    }

    public void update(Artist artist) {
        Optional<Artist> temp = artistRepository.findById(artist.getId());
        if (temp.isPresent()) {
            artistRepository.deleteById(artist.getId());
        }
        artistRepository.save(artist);
    }
}
