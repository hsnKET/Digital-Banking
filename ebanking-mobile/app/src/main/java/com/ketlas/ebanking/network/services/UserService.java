package com.ketlas.ebanking.network.services;

import com.ketlas.ebanking.module.Customer;
import com.ketlas.ebanking.network.TokenResponse;
import com.ketlas.ebanking.network.UserLogin;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface UserService {
    @POST("login")
    Call<TokenResponse> login(@Body UserLogin userLogin);

    @GET("customer")
    Call<Customer> customers();

}
