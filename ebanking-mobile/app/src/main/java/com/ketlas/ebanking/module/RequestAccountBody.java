package com.ketlas.ebanking.module;

public class RequestAccountBody {

    private Long id;

    public RequestAccountBody(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
