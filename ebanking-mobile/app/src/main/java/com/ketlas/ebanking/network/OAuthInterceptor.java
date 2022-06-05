package com.ketlas.ebanking.network;

import android.content.Context;
import android.util.Log;

import com.ketlas.ebanking.util.Const;
import com.ketlas.ebanking.util.SharedPreferenceManager;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class OAuthInterceptor implements Interceptor {


    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
//        String token="Bearer "+Const.ACCESS_TOKEN;
        request = request.newBuilder().header("Authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJoYXNzYW4iLCJyb2xlcyI6WyJVU0VSIl0sImlzcyI6Imh0dHA6Ly9sb2NhbGhvc3Q6OTk5OS9sb2dpbiIsImV4cCI6MTY1NDY1Njc1MH0.tkn6ZpfMAy9-Xwm8PWM1ofr6Mo4AbX0yjgusyfrk_Pw").build();
        return chain.proceed(request);
    }
}
