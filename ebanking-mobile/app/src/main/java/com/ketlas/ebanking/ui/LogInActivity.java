package com.ketlas.ebanking.ui;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.ketlas.ebanking.R;
import com.ketlas.ebanking.network.RetrofitClient;
import com.ketlas.ebanking.network.TokenResponse;
import com.ketlas.ebanking.network.UserLogin;
import com.ketlas.ebanking.network.services.UserService;
import com.ketlas.ebanking.util.AnimationManager;
import com.ketlas.ebanking.util.SharedPreferenceManager;
import com.ketlas.ebanking.util.ToastManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LogInActivity extends AppCompatActivity {

    private ImageView splash_iv;
    private Button loginBtn;
    private EditText username,password;
    private SharedPreferenceManager sharedPreferenceManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_main);
        splash_iv = findViewById(R.id.splash_iv);
        loginBtn = findViewById(R.id.loginBtn);
        username = findViewById(R.id.username_tv);
        password = findViewById(R.id.password_tv);
        sharedPreferenceManager = new SharedPreferenceManager(this);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String  userName=username.getText().toString();
                String pwd = password.getText().toString();
                login(userName,pwd);
            }
        });
        AnimationManager.RevealAnim(splash_iv,new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                splash_iv.setVisibility(View.GONE);
            }
        },AnimationManager.RevealType.REVERSE);

    }

    private void login(String userName,String password){
        UserService userService = RetrofitClient.getClient(this).create(UserService.class);
        userService.login(new UserLogin(userName,password))
                .enqueue(new Callback<TokenResponse>() {
                    @Override
                    public void onResponse(Call<TokenResponse> call, Response<TokenResponse> response) {

                        sharedPreferenceManager.putAccessToken(response.body().getAccess_token());
                        sharedPreferenceManager.putRefreshToken(response.body().getRefresh_token());
                        ToastManager.success(LogInActivity.this,(ViewGroup)splash_iv.getParent(),getString(R.string.msg_success_login));
                        Intent i = new Intent(LogInActivity.this,MainActivity.class);
                        i.putExtra("username",username.getText().toString());
                        startActivity(i);
                        finish();

                    }

                    @Override
                    public void onFailure(Call<TokenResponse> call, Throwable t) {
                        ToastManager.error(LogInActivity.this,(ViewGroup)splash_iv.getParent(),getString(R.string.msg_error_login));

                    }
                });
    }
}