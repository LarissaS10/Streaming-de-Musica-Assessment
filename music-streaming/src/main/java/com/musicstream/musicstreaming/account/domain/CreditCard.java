package com.musicstream.musicstreaming.account.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class CreditCard {

    @Column(name = "card_number")
    private String number;

    @Column(name = "card_holder_name")
    private String holderName;

    @Column(name = "card_expiration_date")
    private String expirationDate;

    @Column(name = "card_active")
    private boolean active;

    protected CreditCard() {}

    public CreditCard(String number, String holderName, String expirationDate, boolean active) {
        this.number = number;
        this.holderName = holderName;
        this.expirationDate = expirationDate;
        this.active = active;
    }

    public boolean isValid() {
        return number != null && !number.isBlank()
                && expirationDate != null && !expirationDate.isBlank();
    }

    public boolean isActive() { return active; }
    public String getNumber() { return number; }
    public String getHolderName() { return holderName; }
    public String getExpirationDate() { return expirationDate; }
}