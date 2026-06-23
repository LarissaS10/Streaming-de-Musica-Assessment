package com.musicstream.musicstreaming.transaction.domain.rules;

import com.musicstream.musicstreaming.transaction.domain.FraudRule;
import org.springframework.stereotype.Component;

@Component
public class CartaoNaoAtivoRule implements FraudRule {
    @Override
    public boolean isViolated(Context ctx) { return !ctx.cardActive(); }

    @Override
    public String violationName() { return "cartão não ativo"; }
}
