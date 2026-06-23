package com.musicstream.musicstreaming.transaction.infrastructure;

import com.musicstream.musicstreaming.transaction.domain.Transaction;
import com.musicstream.musicstreaming.transaction.domain.TransactionRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface JpaTransactionRepository
        extends JpaRepository<Transaction, Long>, TransactionRepository {

    List<Transaction> findByAccountIdAndOccurredAtAfter(Long accountId, LocalDateTime since);

    @Override
    default List<Transaction> findByAccountIdSince(Long accountId, LocalDateTime since) {
        return findByAccountIdAndOccurredAtAfter(accountId, since);
    }
}
