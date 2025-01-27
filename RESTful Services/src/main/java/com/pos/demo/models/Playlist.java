package com.pos.demo.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.util.Set;

@Entity
@Data
@Table(name = "playlists")
@NoArgsConstructor
public class Playlist implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String name;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "playlist_id")
    private Set<Song> songs;

    public Playlist(String name) {
        this.name = name;
    }

    public Playlist(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
}

