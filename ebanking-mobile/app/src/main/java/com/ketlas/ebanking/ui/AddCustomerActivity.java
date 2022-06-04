package com.ketlas.ebanking.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.ketlas.ebanking.R;
import com.ketlas.ebanking.module.Customer;
import com.ketlas.ebanking.network.RetrofitClient;
import com.ketlas.ebanking.network.services.CustomerService;
import com.ketlas.ebanking.util.ToastManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddCustomerActivity extends AppCompatActivity {


    private EditText username;
    private EditText email;
    private Button btnAdd;

    private CustomerService customerService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_customer);
        username = findViewById(R.id.usernameTv);
        email = findViewById(R.id.emailTv);
        btnAdd = findViewById(R.id.btnAdd);

        customerService = RetrofitClient.getClient(this).create(CustomerService.class);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                customerService.save(new Customer(null,username.getText().toString(),email.getText().toString()))
                        .enqueue(new Callback<Customer>() {
                            @Override
                            public void onResponse(Call<Customer> call, Response<Customer> response) {
                                ToastManager.success(AddCustomerActivity.this,(ViewGroup)username.getParent(),"Customer Added!");
                                username.setText("");
                                email.setText("");
                            }
                            @Override
                            public void onFailure(Call<Customer> call, Throwable t) {
                                ToastManager.error(AddCustomerActivity.this,(ViewGroup)username.getParent(),t.getMessage());

                            }
                        });
            }
        });
    }
}