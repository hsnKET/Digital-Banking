package com.ketlas.ebankingbackend.repositories;

import com.ketlas.ebankingbackend.entities.BankAccount;
import com.ketlas.ebankingbackend.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BankAccountRepository extends JpaRepository<BankAccount,String> {

    List<BankAccount> findBankAccountByCustomer(Customer customer);

}
