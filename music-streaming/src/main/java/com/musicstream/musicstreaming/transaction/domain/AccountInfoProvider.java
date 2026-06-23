package com.musicstream.musicstreaming.transaction.domain;

/**
 * Contrato definido por Transaction (Customer).
 * Account (Supplier) implementa em AccountInfoProviderImpl.
 */
public interface AccountInfoProvider {
    boolean isCardValid(Long accountId);
    boolean isCardActive(Long accountId);
}
