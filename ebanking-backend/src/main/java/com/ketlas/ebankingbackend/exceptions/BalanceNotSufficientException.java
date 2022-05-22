package com.ketlas.ebankingbackend.exceptions;

public class BalanceNotSufficientException extends Exception{

    public BalanceNotSufficientException(String message){
        super(message);
    }

}
