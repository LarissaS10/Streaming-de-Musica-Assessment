package com.musicstream.musicstreaming.transaction.domain;

import java.util.List;

/**
 * Strategy Pattern — cada regra antifraude implementa esta interface.
 * Nova regra no futuro = nova classe, sem tocar no código existente (Open/Closed).
 */
public interface FraudRule {

    record Context(Transaction current, boolean cardValid,
                   boolean cardActive, List<Transaction> recent) {}

    boolean isViolated(Context context);
    String violationName();
}
