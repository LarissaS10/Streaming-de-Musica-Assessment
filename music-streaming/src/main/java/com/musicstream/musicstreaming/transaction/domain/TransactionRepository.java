package com.musicstream.musicstreaming.transaction.domain;

import java.time.LocalDateTime;
import java.util.List;

public interface TransactionRepository {
    Transaction save(Transaction transaction);
    List<Transaction> findByAccountIdSince(Long accountId, LocalDateTime since);
}
