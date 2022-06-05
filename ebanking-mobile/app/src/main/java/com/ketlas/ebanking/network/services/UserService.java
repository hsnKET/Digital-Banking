package com.ketlas.ebanking.network.services;

import com.ketlas.ebanking.module.Customer;
import com.ketlas.ebanking.network.model.TokenResponse;
import com.ketlas.ebanking.network.model.UserLogin;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface UserService {
    @POST("login")
    Call<TokenResponse> login(@Body UserLogin userLogin);

    @GET("customer")
    Call<Customer> customers();

}
