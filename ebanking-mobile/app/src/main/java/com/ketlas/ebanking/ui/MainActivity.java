package com.ketlas.ebanking.ui;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ketlas.ebanking.R;
import com.ketlas.ebanking.adapters.CustomerAdp;
import com.ketlas.ebanking.module.Customer;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView sayHi;
    private RecyclerView recyclerView;
    private List<Customer> customers;
    private CustomerAdp customerAdp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

    }
}