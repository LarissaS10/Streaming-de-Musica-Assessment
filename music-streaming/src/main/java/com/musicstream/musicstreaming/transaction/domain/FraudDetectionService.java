package com.musicstream.musicstreaming.transaction.domain;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

/**
 * Domain Service: percorre as regras Strategy e para na primeira violação.
 * O Spring injeta automaticamente todas as classes @Component que implementam FraudRule.
 */
@Service
public class FraudDetectionService {

    private final List<FraudRule> rules;

    public FraudDetectionService(List<FraudRule> rules) {
        this.rules = rules;
    }

    public Optional<String> findFirstViolation(FraudRule.Context context) {
        return rules.stream()
                .filter(rule -> rule.isViolated(context))
                .map(FraudRule::violationName)
                .findFirst();
    }
}
