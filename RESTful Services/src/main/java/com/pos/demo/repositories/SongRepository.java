package com.pos.demo.repositories;

import com.pos.demo.models.Song;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface SongRepository extends JpaRepository<Song, Integer>, PagingAndSortingRepository<Song, Integer> {
    List<Song> findByParent(int parent);
    Page<Song> findAll(Pageable pageable);

}
