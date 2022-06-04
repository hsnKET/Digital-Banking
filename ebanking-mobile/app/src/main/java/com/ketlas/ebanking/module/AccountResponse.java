package com.ketlas.ebanking.module;

import java.util.Date;

public class AccountResponse extends Account{

    private double overDraft;
    private double interestRate;

    public AccountResponse(String id, double balance, Date createdAt, String status) {
        super(id, balance, createdAt, status);
    }

    public double getOverDraft() {
        return overDraft;
    }

    public void setOverDraft(double overDraft) {
        this.overDraft = overDraft;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }
}
