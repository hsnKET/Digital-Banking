package com.ketlas.ebankingbackend.exceptions;

public class CustomerNotFoundException extends Exception{

    public CustomerNotFoundException(String message){
        super(message);
    }

}