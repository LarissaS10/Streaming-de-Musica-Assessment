package com.musicstream.musicstreaming.transaction.application;

import com.musicstream.musicstreaming.transaction.domain.Transaction;
import java.math.BigDecimal;

/** Intention-Revealing Interface para "Autorização de transação". */
public interface TransactionAuthorizationService {
    Transaction authorize(Long accountId, BigDecimal amount, String merchant);
}
