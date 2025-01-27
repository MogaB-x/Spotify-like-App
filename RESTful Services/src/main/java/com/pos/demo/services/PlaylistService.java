package com.pos.demo.services;

import com.pos.demo.models.Artist;
import com.pos.demo.models.Playlist;
import com.pos.demo.models.Song;
import com.pos.demo.repositories.ArtistRepository;
import com.pos.demo.repositories.PlaylistRepository;
import com.pos.demo.repositories.SongRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@NoArgsConstructor
public class PlaylistService {
    @Autowired
    private PlaylistRepository playlistRepository;
    @Autowired
    private SongRepository songRepository;
    @Autowired
    private ArtistRepository artistRepository;

    public void create(Playlist playlist) {
        playlistRepository.save(playlist);
    }

    public void addSongToPlaylist(int playlistId, Song song) {
        Optional<Playlist> playlist = playlistRepository.findById(playlistId);
        playlist.get().getSongs().add(song);
        playlistRepository.save(playlist.get());
    }


    public void removeSongFromPlaylist(int playlistId, Song song) {
        Optional<Playlist> playlist = playlistRepository.findById(playlistId);
        if (playlist.isEmpty())
            return;
        playlist.get().getSongs().remove(song);
        playlistRepository.save(playlist.get());
    }

    public List<Playlist> findAll() {
        return playlistRepository.findAll();
    }

    public void delete(int id) {
        playlistRepository.deleteById(id);
    }

    public Playlist findById(int id) {
        Optional<Playlist> playlist = playlistRepository.findById(id);
        if (playlist.isPresent())
            return playlist.get();
        return null;
    }

    public void update(Playlist playlist) {
        Optional<Playlist> temp = playlistRepository.findById(playlist.getId());
        if (temp.isPresent()) {
            playlistRepository.deleteById(playlist.getId());
        }
        playlistRepository.save(playlist);
    }
}
