package com.ketlas.ebankingbackend.dtos;

import lombok.Data;

import java.util.List;

@Data
public class CustomersPageDTO {

    private int currentPage;
    private int totalPages;
    private int pageSize;
    private List<CustomerDTO> customerDTOS;
}
