package com.ketlas.ebanking.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.service.controls.actions.FloatAction;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.ketlas.ebanking.R;
import com.ketlas.ebanking.adapters.CustomerAdp;
import com.ketlas.ebanking.adapters.SwipeToDeleteCallback;
import com.ketlas.ebanking.callbacks.CustomerListener;
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

public class MainActivity extends AppCompatActivity implements CustomerListener {

    private TextView sayHi;
    private RecyclerView recyclerView;
    private List<Customer> customers;
    private CustomerAdp customerAdp;
    private CustomerService customerService;
    private FloatingActionButton btnAddCustomer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        customerService = RetrofitClient.getClient(this).create(CustomerService.class);

        customers = new ArrayList<>();
        customerAdp = new CustomerAdp(customers,this);
        customerAdp.setCustomerListener(this);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(customerAdp);

        init();

        enableSwipeToDeleteAndUndo();
        btnAddCustomer = findViewById(R.id.btnAddCustomer);
        btnAddCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,AddCustomerActivity.class));
            }
        });
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

    private void enableSwipeToDeleteAndUndo() {
        SwipeToDeleteCallback swipeToDeleteCallback = new SwipeToDeleteCallback(this) {
            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {


                final int position = viewHolder.getAdapterPosition();
                final Customer item = customers.get(position);

                customerAdp.removeItem(position);


                Snackbar snackbar = Snackbar
                        .make(recyclerView, "Customer Deleted!", Snackbar.LENGTH_LONG);
                snackbar.setAction("UNDO", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        customerAdp.restoreItem(item, position);
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

    private void delete(Long id){
        customerService.delete(id)
                .enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                    }
                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                    }
                });

    }
    @Override
    public void onCustomerClicked(int pos) {
        Intent i = new Intent(MainActivity.this,AccountActivity.class);
        i.putExtra("id",(Long)customers.get(pos).getId());
        startActivity(i);
    }

    @Override
    protected void onResume() {
        super.onResume();
        init();
    }
}