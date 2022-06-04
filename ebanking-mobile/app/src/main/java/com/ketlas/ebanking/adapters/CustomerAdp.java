package com.ketlas.ebanking.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ketlas.ebanking.R;
import com.ketlas.ebanking.callbacks.CustomerListener;
import com.ketlas.ebanking.module.Customer;

import java.util.List;

public class CustomerAdp extends RecyclerView.Adapter<CustomerAdp.ViewHolder>{


    private List<Customer> customers;
    private Context context;

    public void setCustomerListener(CustomerListener customerListener) {
        this.customerListener = customerListener;
    }

    private CustomerListener customerListener;

    public CustomerAdp(List<Customer> customers, Context context) {
        this.customers = customers;
        this.context = context;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public void removeItem(int position) {
        customers.remove(position);
        notifyItemRemoved(position);
    }

    public void restoreItem(Customer customer, int position) {
        customers.add(position, customer);
        notifyItemInserted(position);
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.customer_item,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.bind(customers.get(position));
    }

    @Override
    public int getItemCount() {
        return customers.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView username;
        private TextView email;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.username = itemView.findViewById(R.id.usernameTv);
            this.email = itemView.findViewById(R.id.emailTv);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (customerListener!=null)
                    customerListener.onCustomerClicked(getAdapterPosition());
                }
            });
        }

        public void bind(Customer customer){
            this.username.setText(customer.getUsername());
            this.username.setText(customer.getEmail());
        }
    }
}
