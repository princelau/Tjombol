package com.example.tjombol.Adapters;

import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tjombol.databinding.TransactionCardSmallBinding;
import com.example.tjombol.db.TxEntity;
import com.example.tjombol.remote.UserConstant;
import com.example.tjombol.views.Base.BaseAdapter;
import com.example.tjombol.views.UpcomingSalaryFragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UpcomingAdapter extends BaseAdapter<UpcomingAdapter.UpViewHolder, TxEntity> {

    private static final String TAG = "Upcoming_Adapter";
    private List<TxEntity> transactions;
    //private UpcomingSalaryFragment mFragment;
    private UserConstant userConstant;


    public UpcomingAdapter() {
        transactions = new ArrayList<>();
        //this.mFragment = mFragment;
    }

    @Override
    public void setData(List<TxEntity> transactions) {
        this.transactions = transactions;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public UpcomingAdapter.UpViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return UpViewHolder.create(LayoutInflater.from(parent.getContext()), parent);
    }

    @Override
    public void onBindViewHolder(@NonNull final UpcomingAdapter.UpViewHolder holder, int position) {
        TxEntity currentTransaction = transactions.get(position);
        holder.onBind(currentTransaction);
        userConstant = UserConstant.getInstance();

        Log.d(TAG, "setData() is called: /n"
                + Arrays.toString(new String[]{String.valueOf(currentTransaction.getTransactionStatus()),
                currentTransaction.getSender(),
                currentTransaction.getReceiver(),
                //currentTransaction.getType(),
                currentTransaction.getAmount(),
                currentTransaction.getDate(),
                currentTransaction.getDate(),
                currentTransaction.getComments()}));

        String amountWithType;
        //Set Text Color
        if (currentTransaction.getTransactionStatus() == 0) {
            Log.d(TAG, "txStatus: out of my account 0 pending");
            holder.binding.amount.setTextColor(Color.parseColor("#FF9800"));

            amountWithType = "$" + currentTransaction.getAmount();
        } else if (currentTransaction.getTransactionStatus() == 1) {
            Log.d(TAG, "txStatus: out of my account 1 completed");
            holder.binding.amount.setTextColor(Color.parseColor("#E03F35"));
            amountWithType = "-$" + currentTransaction.getAmount();
        } else {
            Log.d(TAG, "txStatus: into my account 0/1 from: " + userConstant.getCompany_Name());
            if (currentTransaction.getTransactionStatus() == 2) {
                // Incoming
                holder.binding.amount.setTextColor(Color.parseColor("#67d6d3"));
            } else {
                // transaction in
                holder.binding.amount.setTextColor(Color.parseColor("#47AD51"));
            }
            amountWithType = "+$" + currentTransaction.getAmount();
        }
        // Set text after color change
        holder.binding.amount.setText(amountWithType);
        holder.binding.sender.setText(userConstant.getCompany_Name());
        // Set onclick listener to each item

        holder.itemView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Toast.makeText(holder.itemView.getContext(), "Item " + position + " is clicked.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return transactions.size();
    }

    //Binding
    static class UpViewHolder extends RecyclerView.ViewHolder {
        private static UpViewHolder create(LayoutInflater inflater, ViewGroup parent) {
            TransactionCardSmallBinding transactionCardSmallBinding = TransactionCardSmallBinding
                    .inflate(inflater, parent, false);
            return new UpViewHolder(transactionCardSmallBinding);
        }

        final TransactionCardSmallBinding binding;

        private UpViewHolder(TransactionCardSmallBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        private void onBind(TxEntity txEntity) {
            binding.setTx(txEntity);
            binding.executePendingBindings();
        }
    }

}
