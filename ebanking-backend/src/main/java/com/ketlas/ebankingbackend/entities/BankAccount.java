package com.ketlas.ebankingbackend.entities;

import com.ketlas.ebankingbackend.enums.AccountStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type",length = 2,discriminatorType = DiscriminatorType.STRING)
public class BankAccount {

    @Id
    private String id;
    private double balance;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Enumerated(EnumType.STRING)
    private AccountStatus status;
    @ManyToOne
    private Customer customer;

    @OneToMany(mappedBy = "bankAccount",fetch = FetchType.LAZY)
    private List<AccountOperation> accountOperations;

}
