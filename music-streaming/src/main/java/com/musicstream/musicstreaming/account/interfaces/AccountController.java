package com.musicstream.musicstreaming.account.interfaces;

import com.musicstream.musicstreaming.account.domain.Account;
import com.musicstream.musicstreaming.account.application.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    // DTOs inline — sem arquivo separado para cada um
    record CreateAccountRequest(String name, String email, String cardNumber,
                                String holderName, String expirationDate) {}
    record SubscribeRequest(String planName) {}
    record FavoriteSongRequest(String songId) {}
    record CreatePlaylistRequest(String playlistName) {}

    record AccountResponse(Long id, String name, String email,
                           String activePlan, java.util.List<String> favoriteSongs) {
        static AccountResponse from(Account a) {
            String plan = a.getActiveSubscription() != null
                    ? a.getActiveSubscription().getPlanName() : null;
            return new AccountResponse(a.getId(), a.getName(),
                    a.getEmail(), plan, a.getFavoriteSongs());
        }
    }

    // Operação 1: Criação de conta
    @PostMapping
    public ResponseEntity<AccountResponse> createAccount(@RequestBody CreateAccountRequest req) {
        Account account = accountService.createAccount(req.name(), req.email(),
                req.cardNumber(), req.holderName(), req.expirationDate());
        return ResponseEntity.status(HttpStatus.CREATED).body(AccountResponse.from(account));
    }

    // Operação 5: Assinatura
    @PostMapping("/{id}/subscriptions")
    public ResponseEntity<AccountResponse> subscribe(@PathVariable Long id,
                                                     @RequestBody SubscribeRequest req) {
        return ResponseEntity.ok(AccountResponse.from(
                accountService.subscribeToPlan(id, req.planName())));
    }

    // Operação 3: Favoritar músicas
    @PostMapping("/{id}/favorites")
    public ResponseEntity<AccountResponse> favorite(@PathVariable Long id,
                                                    @RequestBody FavoriteSongRequest req) {
        return ResponseEntity.ok(AccountResponse.from(
                accountService.favoriteSong(id, req.songId())));
    }

    // Operação 4: Playlist
    @PostMapping("/{id}/playlists")
    public ResponseEntity<AccountResponse> createPlaylist(@PathVariable Long id,
                                                          @RequestBody CreatePlaylistRequest req) {
        return ResponseEntity.status(HttpStatus.CREATED).body(AccountResponse.from(
                accountService.createPlaylist(id, req.playlistName())));
    }
}
