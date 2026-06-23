package com.musicstream.musicstreaming.transaction.domain.rules;

import com.musicstream.musicstreaming.transaction.domain.FraudRule;
import org.springframework.stereotype.Component;

@Component
public class CartaoInvalidoRule implements FraudRule {
    @Override
    public boolean isViolated(Context ctx) { return !ctx.cardValid(); }

    @Override
    public String violationName() { return "cartão inválido"; }
}
