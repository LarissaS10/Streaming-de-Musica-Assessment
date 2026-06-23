package com.musicstream.musicstreaming.transaction.domain.rules;

import com.musicstream.musicstreaming.transaction.domain.FraudRule;
import com.musicstream.musicstreaming.transaction.domain.Transaction;
import org.springframework.stereotype.Component;
import java.time.temporal.ChronoUnit;

@Component
public class TransacaoDuplicadaRule implements FraudRule {

    @Override
    public boolean isViolated(Context ctx) {
        Transaction atual = ctx.current();
        var inicio = atual.getOccurredAt().minus(2, ChronoUnit.MINUTES);
        long semelhantes = ctx.recent().stream()
                .filter(t -> t.getOccurredAt().isAfter(inicio))
                .filter(t -> t.getAmount().compareTo(atual.getAmount()) == 0)
                .filter(t -> t.getMerchant().equals(atual.getMerchant()))
                .count();
        return (semelhantes + 1) > 2;
    }

    @Override
    public String violationName() { return "transação duplicada"; }
}
