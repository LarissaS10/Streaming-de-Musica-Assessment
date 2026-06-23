package com.musicstream.musicstreaming.account.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.time.LocalDate;

@Embeddable
public class Subscription {

    @Column(name = "plan_name")
    private String planName;

    @Column(name = "plan_start_date")
    private LocalDate startDate;

    @Column(name = "plan_active")
    private boolean active;

    protected Subscription() {}

    public Subscription(String planName) {
        this.planName = planName;
        this.startDate = LocalDate.now();
        this.active = true;
    }

    public void deactivate() { this.active = false; }
    public boolean isActive() { return active; }
    public String getPlanName() { return planName; }
}