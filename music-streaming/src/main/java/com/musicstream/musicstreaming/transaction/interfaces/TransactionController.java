package com.musicstream.musicstreaming.transaction.interfaces;

import com.musicstream.musicstreaming.transaction.application.TransactionAuthorizationService;
import com.musicstream.musicstreaming.transaction.domain.Transaction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    private final TransactionAuthorizationService authorizationService;

    public TransactionController(TransactionAuthorizationService authorizationService) {
        this.authorizationService = authorizationService;
    }

    record AuthorizeRequest(Long accountId, BigDecimal amount, String merchant) {}

    record TransactionResponse(Long id, Long accountId, BigDecimal amount,
                               String merchant, LocalDateTime occurredAt,
                               String status, String denialReason) {
        static TransactionResponse from(Transaction t) {
            return new TransactionResponse(t.getId(), t.getAccountId(), t.getAmount(),
                    t.getMerchant(), t.getOccurredAt(), t.getStatus().name(), t.getDenialReason());
        }
    }

    // Operação 2: Autorização de transação
    @PostMapping
    public ResponseEntity<TransactionResponse> authorize(@RequestBody AuthorizeRequest req) {
        Transaction transaction = authorizationService.authorize(
                req.accountId(), req.amount(), req.merchant());
        return ResponseEntity.ok(TransactionResponse.from(transaction));
    }
}
