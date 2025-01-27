package com.pos.demo.services;

import com.pos.demo.models.Artist;
import com.pos.demo.models.Song;
import com.pos.demo.repositories.ArtistRepository;
import com.pos.demo.repositories.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SongService {
    @Autowired
    private SongRepository songRepository;

    public void create(Song song) {
        songRepository.save(song);
    }

    public List<Song> findAll() {
        return songRepository.findAll();
    }

    public Page<Song> findAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return songRepository.findAll(pageable);
    }

    public void update(Song song) {
        Optional<Song> temp = songRepository.findById(Math.toIntExact(song.getId()));
        if (temp.isPresent()) {
            songRepository.delete(song);
        }
        songRepository.save(song);
    }

    public void delete(int id) {
        songRepository.deleteById(id);
    }

    public Song findById(int id) {
        Optional<Song> song = songRepository.findById(id);
        if (song.isPresent())
            return song.get();
        return null;
    }
}
