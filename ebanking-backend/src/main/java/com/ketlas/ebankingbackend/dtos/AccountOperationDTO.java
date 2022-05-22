package com.ketlas.ebankingbackend.dtos;

import com.ketlas.ebankingbackend.enums.OperationType;
import lombok.Data;


import java.util.Date;

@Data
public class AccountOperationDTO {
    private Long id;
    private Date operationDate;
    private double amount;
    private OperationType type;
    private String description;
}