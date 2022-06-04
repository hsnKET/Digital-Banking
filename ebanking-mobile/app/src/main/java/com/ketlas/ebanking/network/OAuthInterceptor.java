package com.ketlas.ebanking.network;

import android.content.Context;

import com.ketlas.ebanking.util.SharedPreferenceManager;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class OAuthInterceptor implements Interceptor {

    private Context context;
    private SharedPreferenceManager sharedPreferenceManager;

    public OAuthInterceptor(Context context) {
        this.context = context;
        sharedPreferenceManager  = new SharedPreferenceManager(context);
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        if (sharedPreferenceManager.isLogin())
        request = request.newBuilder().header("Authorization", "Bearer "+sharedPreferenceManager.getAccessToken()).build();
        return chain.proceed(request);
    }
}
