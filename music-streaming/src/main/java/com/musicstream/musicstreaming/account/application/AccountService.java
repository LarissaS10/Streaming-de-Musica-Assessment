package com.musicstream.musicstreaming.account.application;

import com.musicstream.musicstreaming.account.domain.Account;

/**
 * Intention-Revealing Interface (Eric Evans, DDD).
 * Os nomes dos métodos revelam a intenção sem precisar abrir a implementação.
 */
public interface AccountService {
    Account createAccount(String name, String email, String cardNumber,
                          String holderName, String expirationDate);
    Account subscribeToPlan(Long accountId, String planName);
    Account favoriteSong(Long accountId, String songId);
    Account createPlaylist(Long accountId, String playlistName);
}
