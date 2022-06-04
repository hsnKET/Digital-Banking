package com.ketlas.ebanking.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ketlas.ebanking.R;
import com.ketlas.ebanking.callbacks.AccountListener;
import com.ketlas.ebanking.callbacks.CustomerListener;
import com.ketlas.ebanking.module.Account;
import com.ketlas.ebanking.module.Customer;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class AccountAdp extends RecyclerView.Adapter<AccountAdp.ViewHolder>{


    private List<Account> accounts;
    private Context context;

    public void setAccountsListener(AccountListener accountListener) {
        this.accountListener = accountListener;
    }

    private AccountListener accountListener;

    public AccountAdp(List<Account> accounts, Context context) {
        this.accounts = accounts;
        this.context = context;
    }



    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.account_item,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.bind(accounts.get(position));
    }

    @Override
    public int getItemCount() {
        return accounts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView idAccountTv;
        private TextView createdAtTv;
        private TextView balanceTv;
        private TextView statusTv;
        private TextView email;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.idAccountTv = itemView.findViewById(R.id.idAccountTv);
            this.createdAtTv = itemView.findViewById(R.id.createdAtTv);
            this.balanceTv = itemView.findViewById(R.id.balanceTv);
            this.statusTv = itemView.findViewById(R.id.statusTv);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (accountListener!=null)
                        accountListener.onAccountClicked(getAdapterPosition());
                }
            });
        }

        public void bind(Account account){

            String pattern = "MM-dd-yyyy";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            String balance = String.format("%.2f", account.getBalance());
            idAccountTv.setText(account.getId());
            createdAtTv.setText(simpleDateFormat.format(account.getCreatedAt()));
            statusTv.setText(account.getStatus());
            balanceTv.setText(balance+" DH");
        }
    }
}
