package com.musicstream.musicstreaming.account.domain;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "playlists")
public class Playlist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ElementCollection
    private List<String> songIds = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    protected Playlist() {}

    public Playlist(String name, Account account) {
        this.name = name;
        this.account = account;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public List<String> getSongIds() { return List.copyOf(songIds); }
}