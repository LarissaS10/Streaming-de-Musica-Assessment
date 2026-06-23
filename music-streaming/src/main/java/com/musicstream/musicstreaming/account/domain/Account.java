package com.musicstream.musicstreaming.account.domain;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Aggregate Root do contexto Account (Conta).
 * Toda regra de negócio relacionada à conta mora aqui — domínio rico.
 */
@Entity
@Table(name = "accounts")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;

    @Embedded
    private CreditCard creditCard;

    @Embedded
    private Subscription activeSubscription;

    @ElementCollection
    private List<String> favoriteSongs = new ArrayList<>();

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Playlist> playlists = new ArrayList<>();

    protected Account() {}

    public Account(String name, String email, CreditCard creditCard) {
        if (creditCard == null || !creditCard.isValid())
            throw new IllegalArgumentException("Cartão de crédito inválido.");
        this.name = name;
        this.email = email;
        this.creditCard = creditCard;
    }

    /** Operação "Assinatura" — regra: só um plano ativo por vez. */
    public void subscribeToPlan(Subscription subscription) {
        if (this.activeSubscription != null && this.activeSubscription.isActive())
            throw new IllegalStateException("Conta já possui um plano ativo.");
        this.activeSubscription = subscription;
    }

    /** Operação "Favoritar Músicas" */
    public void favoriteSong(String songId) {
        if (!favoriteSongs.contains(songId))
            favoriteSongs.add(songId);
    }

    /** Operação "Playlist" */
    public Playlist createPlaylist(String name) {
        Playlist playlist = new Playlist(name, this);
        playlists.add(playlist);
        return playlist;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public CreditCard getCreditCard() { return creditCard; }
    public Subscription getActiveSubscription() { return activeSubscription; }
    public List<String> getFavoriteSongs() { return List.copyOf(favoriteSongs); }
    public List<Playlist> getPlaylists() { return List.copyOf(playlists); }
}