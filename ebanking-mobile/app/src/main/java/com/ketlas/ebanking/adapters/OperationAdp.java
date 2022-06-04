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
import com.ketlas.ebanking.module.Account;
import com.ketlas.ebanking.module.Operation;

import java.text.SimpleDateFormat;
import java.util.List;

public class OperationAdp extends RecyclerView.Adapter<OperationAdp.ViewHolder>{


    private List<Operation> operations;
    private Context context;


    public OperationAdp(List<Operation> operations, Context context) {
        this.operations = operations;
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
        View v = LayoutInflater.from(context).inflate(R.layout.operation_item,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.bind(operations.get(position));
    }

    @Override
    public int getItemCount() {
        return operations.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView operationIdTv;
        private TextView operationDateTv;
        private TextView amountTv;
        private TextView typeTv;
        private TextView descriptionTv;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.operationIdTv = itemView.findViewById(R.id.operationIdTv);
            this.operationDateTv = itemView.findViewById(R.id.operationDateTv);
            this.amountTv = itemView.findViewById(R.id.amountTv);
            this.typeTv = itemView.findViewById(R.id.typeTv);
            this.descriptionTv = itemView.findViewById(R.id.descriptionTv);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
        }

        public void bind(Operation operation){

            String pattern = "MM-dd-yyyy";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            String balance = String.format("%.2f", operation.getAmount());
            operationIdTv.setText(operation.getId()+"");
            operationDateTv.setText(simpleDateFormat.format(operation.getOperationDate()));
            typeTv.setText(operation.getType());
            descriptionTv.setText(operation.getDescription());
            amountTv.setText(balance+" DH");
        }
    }
}
