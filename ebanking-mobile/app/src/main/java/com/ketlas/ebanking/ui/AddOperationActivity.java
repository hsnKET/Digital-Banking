package com.ketlas.ebanking.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.ketlas.ebanking.R;
import com.ketlas.ebanking.module.Operation;
import com.ketlas.ebanking.network.RetrofitClient;
import com.ketlas.ebanking.network.model.Credit;
import com.ketlas.ebanking.network.model.Debit;
import com.ketlas.ebanking.network.model.Transfer;
import com.ketlas.ebanking.network.services.AccountService;
import com.ketlas.ebanking.network.services.OperationService;
import com.ketlas.ebanking.util.ToastManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddOperationActivity extends AppCompatActivity {

    private RadioGroup typeRb;
    private EditText destinationEt;
    private EditText amountEt;
    private EditText descriptionTv;
    private Button btnSave;
    private int checkedRadioId = R.id.DEBIT;
    private OperationService operationService;
    private String accountId ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_operation);

        if (getIntent().getExtras()==null)finish();

        accountId =  getIntent().getStringExtra("id");
        operationService = RetrofitClient.getClient(this).create(OperationService.class);


        typeRb = findViewById(R.id.typeRb);
        destinationEt = findViewById(R.id.destinationEt);
        amountEt = findViewById(R.id.amountEt);
        descriptionTv = findViewById(R.id.descriptionTv);
        btnSave = findViewById(R.id.btnSave);

        typeRb.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                checkedRadioId=checkedId;
                if (checkedId==R.id.DEBIT){
                    destinationEt.setVisibility(View.GONE);

                }else if (checkedId==R.id.CREDIT){
                    destinationEt.setVisibility(View.GONE);
                }
                else if (checkedId==R.id.TRANSFER){
                    destinationEt.setVisibility(View.VISIBLE);

                }
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkedRadioId==R.id.CREDIT){
                    credit();
                }
                else if(checkedRadioId==R.id.DEBIT){
                    debit();
                }
                else if(checkedRadioId==R.id.TRANSFER){
                    transfer();
                }
            }


        });
    }

    private void credit() {
        operationService.credit(new Credit(this.accountId,Double.valueOf(amountEt.getText().toString()),descriptionTv.getText().toString()))
                .enqueue(new Callback<Credit>() {
                    @Override
                    public void onResponse(Call<Credit> call, Response<Credit> response) {
                        if (response.isSuccessful())
                        ToastManager.success(AddOperationActivity.this,(ViewGroup)typeRb.getParent(),"Credit successful!");

                    }

                    @Override
                    public void onFailure(Call<Credit> call, Throwable t) {
                        ToastManager.error(AddOperationActivity.this,(ViewGroup)typeRb.getParent(),"Credit : "+t.getMessage());

                    }
                });
    }

    private void debit() {
        operationService.debit(new Debit(this.accountId,Double.valueOf(amountEt.getText().toString()),descriptionTv.getText().toString()))
                .enqueue(new Callback<Debit>() {
                    @Override
                    public void onResponse(Call<Debit> call, Response<Debit> response) {

                        if (response.isSuccessful())
                        ToastManager.success(AddOperationActivity.this,(ViewGroup)typeRb.getParent(),"Debit successful!");

                    }

                    @Override
                    public void onFailure(Call<Debit> call, Throwable t) {
                        ToastManager.error(AddOperationActivity.this,(ViewGroup)typeRb.getParent(),"Debit : "+t.getMessage());

                    }
                });
    }

    private void transfer() {
        operationService.transfer(new Transfer(this.accountId,destinationEt.getText().toString(),Double.valueOf(amountEt.getText().toString()),descriptionTv.getText().toString()))
                .enqueue(new Callback<Transfer>() {
                    @Override
                    public void onResponse(Call<Transfer> call, Response<Transfer> response) {
                        if (response.isSuccessful())
                        ToastManager.success(AddOperationActivity.this,(ViewGroup)typeRb.getParent(),"Transfer successful!");

                    }

                    @Override
                    public void onFailure(Call<Transfer> call, Throwable t) {
                        ToastManager.error(AddOperationActivity.this,(ViewGroup)typeRb.getParent(),"Transfer : "+t.getMessage());

                    }
                });
    }
}