package com.ketlas.ebanking.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ketlas.ebanking.R;
import com.ketlas.ebanking.adapters.CustomerAdp;
import com.ketlas.ebanking.module.Customer;
import com.ketlas.ebanking.network.RetrofitClient;
import com.ketlas.ebanking.network.services.CustomerService;
import com.ketlas.ebanking.network.services.UserService;
import com.ketlas.ebanking.util.Const;
import com.ketlas.ebanking.util.ToastManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.transform.Result;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private TextView sayHi;
    private RecyclerView recyclerView;
    private List<Customer> customers;
    private CustomerAdp customerAdp;
    private CustomerService customerService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        customerService = RetrofitClient.getClient(this).create(CustomerService.class);

        customers = new ArrayList<>();
        customerAdp = new CustomerAdp(customers,this);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(customerAdp);
        for (int i = 0; i < 20 ; i++) {
            customers.add(new Customer(1l,"has","has@gmail.com"));

        }
        customerAdp.notifyDataSetChanged();

        init();

    }

    private void init(){

        customerService.customers()
                .enqueue(new Callback<List<Customer>>() {
                    @Override
                    public void onResponse(Call<List<Customer>> call, Response<List<Customer>> response) {

                        customers.clear();

                        if (response.isSuccessful()){
                            Log.e(MainActivity.class.getSimpleName(),response.body().size()+"");

                            customers.addAll(response.body());
                            customerAdp.notifyDataSetChanged();
                        }
                        else {
                            Log.e(MainActivity.class.getSimpleName(),response.message());

                        }
                    }

                    @Override
                    public void onFailure(Call<List<Customer>> call, Throwable t) {

                    }
                });

    }
}