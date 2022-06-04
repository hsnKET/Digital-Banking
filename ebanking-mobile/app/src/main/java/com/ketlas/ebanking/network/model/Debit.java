package com.ketlas.ebanking.network.model;

public class Debit {

    private String accountId;
    private double amount;
    private String description;

    public Debit(){}
    public Debit(String accountId, double amount, String description) {
        this.accountId = accountId;
        this.amount = amount;
        this.description = description;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
