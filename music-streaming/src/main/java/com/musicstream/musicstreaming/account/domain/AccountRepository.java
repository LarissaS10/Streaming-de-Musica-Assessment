package com.musicstream.musicstreaming.account.domain;

import java.util.Optional;

/** Repository Pattern — o domínio nunca sabe que existe H2 por baixo. */
public interface AccountRepository {
    Account save(Account account);
    Optional<Account> findById(Long id);
}
