package com.musicstream.musicstreaming.transaction.domain.rules;

import com.musicstream.musicstreaming.transaction.domain.FraudRule;
import org.springframework.stereotype.Component;
import java.time.temporal.ChronoUnit;

@Component
public class AltaFrequenciaPequenoIntervaloRule implements FraudRule {

    @Override
    public boolean isViolated(Context ctx) {
        var inicio = ctx.current().getOccurredAt().minus(2, ChronoUnit.MINUTES);
        long anteriores = ctx.recent().stream()
                .filter(t -> t.getOccurredAt().isAfter(inicio))
                .count();
        return (anteriores + 1) > 3;
    }

    @Override
    public String violationName() { return "alta-frequência-pequeno-intervalo"; }
}
