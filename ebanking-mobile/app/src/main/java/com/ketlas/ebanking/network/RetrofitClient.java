package com.ketlas.ebanking.network;

import android.content.Context;

import com.ketlas.ebanking.util.SharedPreferenceManager;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static Retrofit retrofit = null;
    private static final String url = "http://10.0.2.2:9999/";
    private static SharedPreferenceManager sharedPreferenceManager;


    public static Retrofit getClient(Context context){
        if(retrofit == null){
            sharedPreferenceManager = new SharedPreferenceManager(context);

            OkHttpClient okHttpClient = new OkHttpClient
                    .Builder().addInterceptor(new OAuthInterceptor(context)).build();

            retrofit = new Retrofit.Builder()
                    .client(okHttpClient)
                    .baseUrl(RetrofitClient.url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }




}
