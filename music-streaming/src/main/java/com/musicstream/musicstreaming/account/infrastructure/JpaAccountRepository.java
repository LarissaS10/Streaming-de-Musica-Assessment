package com.musicstream.musicstreaming.account.infrastructure;


import com.musicstream.musicstreaming.account.domain.Account;
import com.musicstream.musicstreaming.account.domain.AccountRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

/**
 * Implementa AccountRepository usando Spring Data JPA com banco H2.
 * A interface JpaRepository já gera o SQL automaticamente.
 */
@Repository
public interface JpaAccountRepository extends JpaRepository<Account, Long>, AccountRepository {
    Optional<Account> findById(Long id);
}
