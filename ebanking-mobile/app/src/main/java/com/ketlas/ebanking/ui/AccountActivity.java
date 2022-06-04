package com.ketlas.ebanking.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.ketlas.ebanking.R;
import com.ketlas.ebanking.adapters.AccountAdp;
import com.ketlas.ebanking.adapters.CustomerAdp;
import com.ketlas.ebanking.callbacks.AccountListener;
import com.ketlas.ebanking.module.Account;
import com.ketlas.ebanking.module.Customer;
import com.ketlas.ebanking.module.RequestAccountBody;
import com.ketlas.ebanking.network.RetrofitClient;
import com.ketlas.ebanking.network.services.AccountService;
import com.ketlas.ebanking.network.services.CustomerService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AccountActivity extends AppCompatActivity implements AccountListener {

    private RecyclerView recyclerView;
    private List<Account> accounts;
    private AccountAdp accountAdp;
    private AccountService accountService;
    private Long customerID = 1l;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        if (!getIntent().hasExtra("id"))
            finish();

        customerID=getIntent().getLongExtra("id",-1);

        setContentView(R.layout.activity_main);
        accountService = RetrofitClient.getClient(this).create(AccountService.class);

        accounts = new ArrayList<>();
        accountAdp = new AccountAdp(accounts,this);
        accountAdp.setAccountsListener(this);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(accountAdp);

        init();

    }

    private void init(){
        accountService.customerAccounts(customerID)
                .enqueue(new Callback<List<Account>>() {
                    @Override
                    public void onResponse(Call<List<Account>> call, Response<List<Account>> response) {
                        accounts.clear();

                        if (response.isSuccessful()){
                            Log.e(MainActivity.class.getSimpleName(),response.body().size()+"");

                            accounts.addAll(response.body());
                            accountAdp.notifyDataSetChanged();
                        }
                        else {
                            Log.e(MainActivity.class.getSimpleName(),response.message());

                        }
                    }

                    @Override
                    public void onFailure(Call<List<Account>> call, Throwable t) {

                    }
                });
    }

    @Override
    public void onAccountClicked(int pos) {

    }
}