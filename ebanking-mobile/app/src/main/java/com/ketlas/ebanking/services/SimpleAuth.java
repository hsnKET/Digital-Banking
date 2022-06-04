package com.ketlas.ebanking.services;

public class SimpleAuth implements LogIn {

    private String username = "admin";
    private String password = "admin";
    @Override
    public boolean auth(String username, String password) {
        return this.username.equalsIgnoreCase(username) && this.password.equalsIgnoreCase(password);
    }
}
