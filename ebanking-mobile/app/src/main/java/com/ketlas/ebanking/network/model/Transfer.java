package com.ketlas.ebanking.network.model;

public class Transfer {

    private String accountSource;
    private String accountDestination;
    private double amount;
    private String description;

    public Transfer(String accountSource, String accountDestination, double amount, String description) {
        this.accountSource = accountSource;
        this.accountDestination = accountDestination;
        this.amount = amount;
        this.description = description;
    }

    public String getAccountSource() {
        return accountSource;
    }

    public void setAccountSource(String accountSource) {
        this.accountSource = accountSource;
    }

    public String getAccountDestination() {
        return accountDestination;
    }

    public void setAccountDestination(String accountDestination) {
        this.accountDestination = accountDestination;
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
