package com.ketlas.ebankingbackend.web;


import com.ketlas.ebankingbackend.dtos.AccountOperationDTO;
import com.ketlas.ebankingbackend.dtos.BankAccountDTO;
import com.ketlas.ebankingbackend.dtos.CustomerDTO;
import com.ketlas.ebankingbackend.exceptions.BankAccountNotFoundException;
import com.ketlas.ebankingbackend.exceptions.CustomerNotFoundException;
import com.ketlas.ebankingbackend.services.BankAccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class BankAccountRestAPI {


    @Autowired
    private BankAccountService bankAccountService;

    @GetMapping("/accounts/{id}")
    BankAccountDTO  getBankAccount(@PathVariable("id") String id) throws BankAccountNotFoundException {
        return bankAccountService.getBankAccount(id);
    }

    @GetMapping("/accounts")
    List<BankAccountDTO> listAccounts(){
        return bankAccountService.bankAccountList();
    }


    @GetMapping("/accounts/{accountId}/operations")
    List<AccountOperationDTO> getHistories(@PathVariable String accountId){
        return bankAccountService.accountHistory(accountId);
    }


}
