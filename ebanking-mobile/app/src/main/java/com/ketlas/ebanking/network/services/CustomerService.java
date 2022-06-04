package com.ketlas.ebanking.network.services;

import com.ketlas.ebanking.module.Customer;
import com.ketlas.ebanking.network.TokenResponse;
import com.ketlas.ebanking.network.UserLogin;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface CustomerService {
    @GET("customers")
    Call<List<Customer>> customers();


}
