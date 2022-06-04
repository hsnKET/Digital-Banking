package com.ketlas.ebanking.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.ketlas.ebanking.R;
import com.ketlas.ebanking.adapters.AccountAdp;
import com.ketlas.ebanking.adapters.CustomerAdp;
import com.ketlas.ebanking.adapters.SwipeToDeleteCallback;
import com.ketlas.ebanking.callbacks.AccountListener;
import com.ketlas.ebanking.module.Account;
import com.ketlas.ebanking.module.Customer;
import com.ketlas.ebanking.module.RequestAccountBody;
import com.ketlas.ebanking.network.RetrofitClient;
import com.ketlas.ebanking.network.services.AccountService;
import com.ketlas.ebanking.network.services.CustomerService;
import com.ketlas.ebanking.util.Const;

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
    private FloatingActionButton btnSaveAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        if (!getIntent().hasExtra("id"))
            finish();
        btnSaveAccount = findViewById(R.id.btnSaveAccount);
        customerID=getIntent().getLongExtra("id",-1);

        btnSaveAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(AccountActivity.this,AddAccountActivity.class);
                i.putExtra("id",customerID);
                startActivity(i);
                Toast.makeText(AccountActivity.this, "clicked", Toast.LENGTH_SHORT).show();

            }
        });
        accountService = RetrofitClient.getClient(this).create(AccountService.class);

        accounts = new ArrayList<>();
        accountAdp = new AccountAdp(accounts,this);
        accountAdp.setAccountsListener(this);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(accountAdp);

        init();
        enableSwipeToDeleteAndUndo();


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

    private void enableSwipeToDeleteAndUndo() {
        SwipeToDeleteCallback swipeToDeleteCallback = new SwipeToDeleteCallback(this) {
            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {


                final int position = viewHolder.getAdapterPosition();
                final Account item = accounts.get(position);

                accountAdp.removeItem(position);


                Snackbar snackbar = Snackbar
                        .make(recyclerView, "Account Deleted!", Snackbar.LENGTH_LONG);
                snackbar.setAction("UNDO", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        accountAdp.restoreItem(item, position);
                        recyclerView.scrollToPosition(position);
                    }
                });

                snackbar.setActionTextColor(Color.YELLOW);
                snackbar.show();
                snackbar.addCallback(new Snackbar.Callback() {

                    @Override
                    public void onDismissed(Snackbar snackbar, int event) {
                        delete(item.getId());
                    }

                    @Override
                    public void onShown(Snackbar snackbar) {
                    }
                });

            }
        };

        ItemTouchHelper itemTouchhelper = new ItemTouchHelper(swipeToDeleteCallback);
        itemTouchhelper.attachToRecyclerView(recyclerView);
    }

    private void delete(String id) {
        accountService.deleteAccount(id)
                .enqueue(new Callback<List<Account>>() {
                    @Override
                    public void onResponse(Call<List<Account>> call, Response<List<Account>> response) {

                    }

                    @Override
                    public void onFailure(Call<List<Account>> call, Throwable t) {

                    }
                });
    }

    @Override
    public void onAccountClicked(int pos) {
        Intent i = new Intent(AccountActivity.this,OperationActivity.class);
        i.putExtra("id",accounts.get(pos).getId());
        startActivity(i);
    }
}