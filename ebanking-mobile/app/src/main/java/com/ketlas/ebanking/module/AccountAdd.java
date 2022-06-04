package com.ketlas.ebanking.module;

public class AccountAdd {
    private long idCustomer;
    private float initialBalance;
    private String type;
    private float amountType;

    public AccountAdd(long idCustomer, float initialBalance, String type, float amountType) {
        this.idCustomer = idCustomer;
        this.initialBalance = initialBalance;
        this.type = type;
        this.amountType = amountType;
    }

    public long getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(long idCustomer) {
        this.idCustomer = idCustomer;
    }

    public float getInitialBalance() {
        return initialBalance;
    }

    public void setInitialBalance(float initialBalance) {
        this.initialBalance = initialBalance;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public float getAmountType() {
        return amountType;
    }

    public void setAmountType(float amountType) {
        this.amountType = amountType;
    }
}
