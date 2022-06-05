package com.ketlas.ebanking.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.ketlas.ebanking.R;
import com.ketlas.ebanking.module.AccountAdd;
import com.ketlas.ebanking.module.AccountResponse;
import com.ketlas.ebanking.network.RetrofitClient;
import com.ketlas.ebanking.network.services.AccountService;
import com.ketlas.ebanking.util.ToastManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddAccountActivity extends AppCompatActivity {

    private RadioGroup typeRb;
    private EditText initialBalance;
    private EditText amountType;
    private Button btnAdd;
    private AccountService accountService;
    private Long customerID = 1l;
    private boolean isCurrentChecked = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_acount);

//        if (!getIntent().hasExtra("id"))
//            finish();


        customerID=getIntent().getLongExtra("id",-1);


        accountService = RetrofitClient.getClient(this).create(AccountService.class);

        typeRb = findViewById(R.id.typeRb);
        initialBalance = findViewById(R.id.initialBalance);
        amountType = findViewById(R.id.amountType);
        btnAdd = findViewById(R.id.btnAdd);

        typeRb.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId==R.id.currentRb){
                    isCurrentChecked = true;
                    amountType.setHint("Over Draft");
                }else if (checkedId==R.id.savingRb){
                    isCurrentChecked = false;
                    amountType.setHint("Interest Rate");
                }
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                save();
            }
        });

    }

    private void save(){
        String type =isCurrentChecked?"current":"saving";
        accountService.save(new AccountAdd(customerID,
                Float.valueOf(initialBalance.getText().toString()),type,
                Float.valueOf(amountType.getText().toString())))
                .enqueue(new Callback<AccountResponse>() {
                    @Override
                    public void onResponse(Call<AccountResponse> call, Response<AccountResponse> response) {
                        ToastManager.success(AddAccountActivity.this,(ViewGroup)typeRb.getParent(),"Account Added!");
                        initialBalance.setText("");
                        amountType.setText("");

                    }

                    @Override
                    public void onFailure(Call<AccountResponse> call, Throwable t) {
                        ToastManager.error(AddAccountActivity.this,(ViewGroup)typeRb.getParent(),t.getMessage());

                    }
                });
    }
}