package com.ketlas.ebankingbackend.web;


import com.ketlas.ebankingbackend.dtos.CustomerDTO;
import com.ketlas.ebankingbackend.entities.Customer;
import com.ketlas.ebankingbackend.services.BankAccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
//@RequestMapping("customers")
@Slf4j
public class CustomerController {


    @Autowired
    private BankAccountService bankAccountService;

    @GetMapping("/customers")
    List<CustomerDTO> listCustomers(){
        return bankAccountService.listCustomers();
    }

}
