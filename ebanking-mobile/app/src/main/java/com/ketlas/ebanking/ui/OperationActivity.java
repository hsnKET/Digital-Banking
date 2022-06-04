package com.ketlas.ebanking.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.ketlas.ebanking.R;
import com.ketlas.ebanking.adapters.AccountAdp;
import com.ketlas.ebanking.adapters.OperationAdp;
import com.ketlas.ebanking.module.Account;
import com.ketlas.ebanking.module.Operation;
import com.ketlas.ebanking.network.RetrofitClient;
import com.ketlas.ebanking.network.services.AccountService;
import com.ketlas.ebanking.network.services.OperationService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OperationActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<Operation> operations;
    private OperationAdp operationAdp;
    private OperationService operationService;
    private String accountId;
    private FloatingActionButton btnGo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        if (!getIntent().hasExtra("id"))
            finish();



        accountId=getIntent().getStringExtra("id");
        setContentView(R.layout.activity_main);

        operationService = RetrofitClient.getClient(this).create(OperationService.class);

        operations = new ArrayList<>();
        operationAdp = new OperationAdp(operations,this);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(operationAdp);

        init();


        btnGo = findViewById(R.id.btnGo);
        btnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(OperationActivity.this,AddOperationActivity.class);
                i.putExtra("id",accountId);
                startActivity(i);
            }
        });


    }

    private void init(){
        operationService.AccountOperation(accountId)
                .enqueue(new Callback<List<Operation>>() {
                    @Override
                    public void onResponse(Call<List<Operation>> call, Response<List<Operation>> response) {
                        operations.clear();

                        if (response.isSuccessful()){
                            Log.e(MainActivity.class.getSimpleName(),response.body().size()+"");

                            operations.addAll(response.body());
                            operationAdp.notifyDataSetChanged();
                        }
                        else {
                            Log.e(MainActivity.class.getSimpleName(),response.message());

                        }
                    }

                    @Override
                    public void onFailure(Call<List<Operation>> call, Throwable t) {

                    }
                });
    }


}