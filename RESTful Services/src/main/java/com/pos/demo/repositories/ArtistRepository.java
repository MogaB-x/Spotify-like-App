package com.pos.demo.repositories;

import com.pos.demo.models.Artist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArtistRepository extends JpaRepository<Artist, Integer> {
    List<Artist> findAllByName(String name);
    List<Artist> findByNameContaining(String name);
}
