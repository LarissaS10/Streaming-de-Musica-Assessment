package com.musicstream.musicstreaming.account.infrastructure;


import com.musicstream.musicstreaming.account.domain.Account;
import com.musicstream.musicstreaming.account.domain.AccountRepository;
import com.musicstream.musicstreaming.transaction.domain.AccountInfoProvider;
import org.springframework.stereotype.Component;

/**
 * Lado "Supplier" do Context Mapping Customer-Supplier.
 * Transaction (Customer) define o contrato via AccountInfoProvider.
 * Account (Supplier) implementa aqui.
 */
@Component
public class AccountInfoProviderImpl implements AccountInfoProvider {

    private final AccountRepository accountRepository;

    public AccountInfoProviderImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public boolean isCardValid(Long accountId) {
        return find(accountId).getCreditCard().isValid();
    }

    @Override
    public boolean isCardActive(Long accountId) {
        return find(accountId).getCreditCard().isActive();
    }

    private Account find(Long accountId) {
        return accountRepository.findById(accountId)
                .orElseThrow(() -> new IllegalArgumentException("Conta não encontrada: " + accountId));
    }
}
