package com.ketlas.ebankingbackend.repositories;

import com.ketlas.ebankingbackend.entities.AccountOperation;
import com.ketlas.ebankingbackend.entities.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer,Long> {


    @Query("select c from Customer c where c.name like :kw")
    List<Customer> searchCustomer(@Param("kw") String keyword);

    @Query("select c from Customer c where c.name like :name")
    public Page<Customer> searchCustomers(String name, Pageable pageable);
}
