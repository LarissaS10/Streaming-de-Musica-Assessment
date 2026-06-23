package com.musicstream.musicstreaming.account.application;

import com.musicstream.musicstreaming.account.domain.*;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Account createAccount(String name, String email, String cardNumber,
                                 String holderName, String expirationDate) {
        CreditCard card = new CreditCard(cardNumber, holderName, expirationDate, true);
        return accountRepository.save(new Account(name, email, card));
    }

    @Override
    public Account subscribeToPlan(Long accountId, String planName) {
        Account account = find(accountId);
        account.subscribeToPlan(new Subscription(planName));
        return accountRepository.save(account);
    }

    @Override
    public Account favoriteSong(Long accountId, String songId) {
        Account account = find(accountId);
        account.favoriteSong(songId);
        return accountRepository.save(account);
    }

    @Override
    public Account createPlaylist(Long accountId, String playlistName) {
        Account account = find(accountId);
        account.createPlaylist(playlistName);
        return accountRepository.save(account);
    }

    private Account find(Long id) {
        return accountRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Conta não encontrada: " + id));
    }
}
