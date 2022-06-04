package com.ketlas.ebankingbackend.entities;

import lombok.Data;

@Data
public class AccountAdd {

    private long idCustomer;
    private float initialBalance;
    private String type;
    private float amountType;
}
