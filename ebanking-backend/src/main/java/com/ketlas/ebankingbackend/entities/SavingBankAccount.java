package com.ketlas.ebankingbackend.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@DiscriminatorValue("SA")
public class SavingBankAccount extends BankAccount{
    private double interestRate;
}
