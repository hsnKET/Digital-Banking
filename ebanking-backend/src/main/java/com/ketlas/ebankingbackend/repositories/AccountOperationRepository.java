package com.ketlas.ebankingbackend.repositories;

import com.ketlas.ebankingbackend.entities.AccountOperation;
import com.ketlas.ebankingbackend.entities.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface AccountOperationRepository extends JpaRepository<AccountOperation,Long> {

    public List<AccountOperation> findByBankAccountId(String accountId);
}
