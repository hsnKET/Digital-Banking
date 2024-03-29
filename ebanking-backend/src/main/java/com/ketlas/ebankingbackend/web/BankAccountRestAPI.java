package com.ketlas.ebankingbackend.web;


import com.ketlas.ebankingbackend.dtos.*;
import com.ketlas.ebankingbackend.entities.AccountAdd;
import com.ketlas.ebankingbackend.exceptions.BalanceNotSufficientException;
import com.ketlas.ebankingbackend.exceptions.BankAccountNotFoundException;
import com.ketlas.ebankingbackend.exceptions.CustomerNotFoundException;
import com.ketlas.ebankingbackend.services.BankAccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@CrossOrigin("*")
public class BankAccountRestAPI {


    @Autowired
    private BankAccountService bankAccountService;

    @GetMapping("/accounts/{id}")
    BankAccountDTO  getBankAccount(@PathVariable("id") String id) throws BankAccountNotFoundException {
        return bankAccountService.getBankAccount(id);
    }

    @PostMapping("/accounts")
    BankAccountDTO  addBankAccount(

            @RequestBody AccountAdd accountAdd
            ) throws CustomerNotFoundException {

        if ("current".equalsIgnoreCase(accountAdd.getType())){
            return bankAccountService.saveCurrentBankAccount(accountAdd.getInitialBalance(),accountAdd.getAmountType(),accountAdd.getIdCustomer());
        }
        return bankAccountService.saveSavingBankAccount(accountAdd.getInitialBalance(),accountAdd.getAmountType(),accountAdd.getIdCustomer());
    }


    @DeleteMapping("/accounts/{id}")
    void  deleteBankAccount(@PathVariable("id") String id)  {
        bankAccountService.deleteBankAccount(id);
    }

    @GetMapping("/accounts")
    List<BankAccountDTO> listAccounts(){
        return bankAccountService.bankAccountList();
    }


    @GetMapping("/accounts/{accountId}/operations")
    List<AccountOperationDTO> getHistories(@PathVariable String accountId){
        return bankAccountService.accountHistory(accountId);
    }

    @GetMapping("/accounts/{accountId}/pageOperations")
    AccountHistoryDTO getAccountHistory(
            @PathVariable String accountId,
            @RequestParam(name = "page",defaultValue = "0") int page,
            @RequestParam(name = "size",defaultValue = "5") int size) throws BankAccountNotFoundException {
        return bankAccountService.getAccountHistory(accountId,page,size);
    }

    @GetMapping("/accounts/{accountId}/operationss")
    List<AccountOperationDTO> getAccountOperations(@PathVariable String accountId) throws BankAccountNotFoundException {
        return bankAccountService.getAccountOperations(accountId);
    }

    @PostMapping("/accounts/debit")
    public DebitDTO debit(@RequestBody DebitDTO debitDTO) throws BankAccountNotFoundException, BalanceNotSufficientException {
        this.bankAccountService.debit(debitDTO.getAccountId(),debitDTO.getAmount(),debitDTO.getDescription());
        return debitDTO;
    }
    @PostMapping("/accounts/credit")
    public CreditDTO credit(@RequestBody CreditDTO creditDTO) throws BankAccountNotFoundException, BalanceNotSufficientException {
        this.bankAccountService.credit(creditDTO.getAccountId(),creditDTO.getAmount(),creditDTO.getDescription());
        return creditDTO;
    }

    @PostMapping("/accounts/transfer")
    public void transfer(@RequestBody TransferRequestDTO transferRequestDTO) throws BankAccountNotFoundException, BalanceNotSufficientException {
        this.bankAccountService.transfer(
                transferRequestDTO.getAccountSource(),
                transferRequestDTO.getAccountDestination(),
                transferRequestDTO.getAmount());
    }

}
