package com.pos.demo.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Data
@Table(name = "songs")
@NoArgsConstructor
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column
    private String songName;
    @Column
    private String gen;
    @Column
    private Integer year;
    @Column
    private String type;
    @Column
    private int parent;

    public Song (String songName, String gen, Integer year, String type, int parent) {
       this.songName = songName;
       this.gen = gen;
       this.year = year;
       this.type = type;
       this.parent = parent;
    }
}

