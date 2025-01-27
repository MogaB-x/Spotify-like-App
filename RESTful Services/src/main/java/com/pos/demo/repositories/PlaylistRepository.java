package com.pos.demo.repositories;

import com.pos.demo.models.Playlist;
import com.pos.demo.models.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlaylistRepository extends JpaRepository<Playlist, Integer> {
}
