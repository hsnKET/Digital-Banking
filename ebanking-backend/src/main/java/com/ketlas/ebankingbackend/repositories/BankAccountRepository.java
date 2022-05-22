package com.ketlas.ebankingbackend.repositories;

import com.ketlas.ebankingbackend.entities.BankAccount;
import com.ketlas.ebankingbackend.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankAccountRepository extends JpaRepository<BankAccount,String> {

}
