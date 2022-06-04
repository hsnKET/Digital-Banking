package com.ketlas.ebanking.network.services;

import com.ketlas.ebanking.module.Account;
import com.ketlas.ebanking.module.Operation;
import com.ketlas.ebanking.network.model.Credit;
import com.ketlas.ebanking.network.model.Debit;
import com.ketlas.ebanking.network.model.Transfer;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface OperationService {
    @GET("accounts/{accountId}/operations")
    Call<List<Operation>> AccountOperation(@Path("accountId") String accountId);

    @POST("/accounts/credit")
    Call<Credit> credit(@Body Credit credit);

    @POST("/accounts/debit")
    Call<Debit> debit(@Body Debit debit);

    @POST("/accounts/transfer")
    Call<Transfer> transfer(@Body Transfer transfer);






}
