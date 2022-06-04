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
        String token="Bearer "+Const.ACCESS_TOKEN;
        request = request.newBuilder().header("Authorization", token).build();
        return chain.proceed(request);
    }
}
