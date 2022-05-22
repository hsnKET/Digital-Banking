package com.ketlas.ebankingbackend.web;


import com.ketlas.ebankingbackend.dtos.CustomerDTO;
import com.ketlas.ebankingbackend.exceptions.CustomerNotFoundException;
import com.ketlas.ebankingbackend.services.BankAccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class CustomerController {


    @Autowired
    private BankAccountService bankAccountService;

    @GetMapping("/customers")
    List<CustomerDTO> listCustomers(){
        return bankAccountService.listCustomers();
    }

    @GetMapping("/customers/{id}")
    CustomerDTO getCustomer(@PathVariable("id") Long id) throws CustomerNotFoundException {
        return bankAccountService.getCustomer(id);
    }


    @PostMapping("/customers")
    public CustomerDTO saveCustomer(@RequestBody CustomerDTO customerDTO){
        return bankAccountService.saveCustomer(customerDTO);
    }
    @PutMapping("/customers/{id}")
    public CustomerDTO updateCustomer(@PathVariable("id") Long customerId, @RequestBody CustomerDTO customerDTO){
        customerDTO.setId(customerId);
        return bankAccountService.updateCustomer(customerDTO);
    }

    @DeleteMapping("/customers/{id}")
    public void deleteCustomer(@PathVariable("id") Long id){
        bankAccountService.deleteCustomer(id);
    }


}
