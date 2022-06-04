package com.ketlas.ebanking.module;

import java.util.Date;

public class Operation {

    private Long id;
    private Date operationDate;
    private double amount;
    private String type;
    private String description;

    public Operation(Long id, Date operationDate, double amount, String type, String description) {
        this.id = id;
        this.operationDate = operationDate;
        this.amount = amount;
        this.type = type;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getOperationDate() {
        return operationDate;
    }

    public void setOperationDate(Date operationDate) {
        this.operationDate = operationDate;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
