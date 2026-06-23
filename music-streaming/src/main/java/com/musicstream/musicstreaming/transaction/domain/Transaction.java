package com.musicstream.musicstreaming.transaction.domain;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Aggregate Root do contexto Transaction (Transação).
 */
@Entity
@Table(name = "transactions")
public class Transaction {

    public enum Status { PENDING, APPROVED, DENIED }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long accountId;
    private BigDecimal amount;
    private String merchant;
    private LocalDateTime occurredAt;

    @Enumerated(EnumType.STRING)
    private Status status;

    private String denialReason;

    protected Transaction() {}

    public Transaction(Long accountId, BigDecimal amount, String merchant) {
        this.accountId = accountId;
        this.amount = amount;
        this.merchant = merchant;
        this.occurredAt = LocalDateTime.now();
        this.status = Status.PENDING;
    }

    public void approve() { this.status = Status.APPROVED; }

    public void deny(String reason) {
        this.status = Status.DENIED;
        this.denialReason = reason;
    }

    public Long getId() { return id; }
    public Long getAccountId() { return accountId; }
    public BigDecimal getAmount() { return amount; }
    public String getMerchant() { return merchant; }
    public LocalDateTime getOccurredAt() { return occurredAt; }
    public Status getStatus() { return status; }
    public String getDenialReason() { return denialReason; }
}
