package com.ketlas.ebanking.network.services;

import com.ketlas.ebanking.module.Customer;
import com.ketlas.ebanking.network.TokenResponse;
import com.ketlas.ebanking.network.UserLogin;

import java.util.List;

import javax.xml.transform.Result;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface CustomerService {
    @GET("customers")
    Call<List<Customer>> customers();

    @DELETE("/customers/{id}")
    Call<Void> delete(@Path("id") Long customerId);

    @POST("/customers")
    Call<Customer> save(@Body Customer customer);



}
