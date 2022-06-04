package com.ketlas.ebanking.network.services;

import com.ketlas.ebanking.module.Account;
import com.ketlas.ebanking.module.Customer;
import com.ketlas.ebanking.module.RequestAccountBody;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface AccountService {
    @GET("customers/accounts")
    Call<List<Account>> customerAccounts(@Query("id") Long id);



}
