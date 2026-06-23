package com.musicstream.musicstreaming.transaction.application;

import com.musicstream.musicstreaming.transaction.domain.*;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.List;

@Service
public class TransactionAuthorizationServiceImpl implements TransactionAuthorizationService {

    private final TransactionRepository transactionRepository;
    private final AccountInfoProvider accountInfoProvider;
    private final FraudDetectionService fraudDetectionService;

    public TransactionAuthorizationServiceImpl(TransactionRepository transactionRepository,
                                               AccountInfoProvider accountInfoProvider,
                                               FraudDetectionService fraudDetectionService) {
        this.transactionRepository = transactionRepository;
        this.accountInfoProvider = accountInfoProvider;
        this.fraudDetectionService = fraudDetectionService;
    }

    @Override
    public Transaction authorize(Long accountId, BigDecimal amount, String merchant) {
        Transaction transaction = new Transaction(accountId, amount, merchant);

        var inicio = transaction.getOccurredAt().minusMinutes(2);
        List<Transaction> historico = transactionRepository.findByAccountIdSince(accountId, inicio);

        FraudRule.Context context = new FraudRule.Context(
                transaction,
                accountInfoProvider.isCardValid(accountId),
                accountInfoProvider.isCardActive(accountId),
                historico
        );

        fraudDetectionService.findFirstViolation(context)
                .ifPresentOrElse(transaction::deny, transaction::approve);

        return transactionRepository.save(transaction);
    }
}
