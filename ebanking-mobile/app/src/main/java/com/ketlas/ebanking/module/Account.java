package com.ketlas.ebanking.module;

import java.util.Date;

public class Account {


    private String id;
    private double balance;
    private Date createdAt;
    private String status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Account(String id, double balance, Date createdAt, String status) {
        this.id = id;
        this.balance = balance;
        this.createdAt = createdAt;
        this.status = status;
    }
}
