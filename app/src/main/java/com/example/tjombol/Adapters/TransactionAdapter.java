package com.example.tjombol.Adapters;

import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tjombol.databinding.TransactionCardBinding;
import com.example.tjombol.db.TxEntity;
import com.example.tjombol.views.Base.BaseAdapter;
import com.example.tjombol.views.TransactionListFragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TransactionAdapter extends BaseAdapter<TransactionAdapter.TxViewHolder, TxEntity> {

    private static final String TAG = "TX_ADAPTER";
    private List<TxEntity> transactions;
    private TransactionListFragment mFragment;

    public TransactionAdapter(TransactionListFragment mFragment) {
        transactions = new ArrayList<>();
        this.mFragment = mFragment;
    }

    @Override
    public void setData(List<TxEntity> transactions) {
        this.transactions = transactions;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TransactionAdapter.TxViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return TxViewHolder.create(LayoutInflater.from(parent.getContext()),parent);
    }

    @Override
    public void onBindViewHolder(@NonNull final TransactionAdapter.TxViewHolder holder, int position) {
        TxEntity currentTransaction = transactions.get(position);
        holder.onBind(currentTransaction);


        Log.e(TAG, "setData() is called: /n"
                + Arrays.toString(new String[]{String.valueOf(currentTransaction.getTransactionStatus()),
                currentTransaction.getSender(),
                currentTransaction.getReceiver(),
                currentTransaction.getType(),
                currentTransaction.getAmount(),
                currentTransaction.getDate(),
                currentTransaction.getDate(),
                currentTransaction.getComments()}));

        String amountWithType;
        //Set Text Color
        if (currentTransaction.getTransactionStatus()==0) {
            holder.binding.amount.setTextColor(Color.parseColor("#47AD51"));
            amountWithType = "+$"+currentTransaction.getAmount();
        }

        else if (currentTransaction.getTransactionStatus()==1) {
            holder.binding.amount.setTextColor(Color.parseColor("#E03F35"));
            amountWithType = "-$"+ currentTransaction.getAmount();
        }
        else{
            holder.binding.amount.setTextColor(Color.parseColor("#FF9800"));
            amountWithType = "-$"+ currentTransaction.getAmount();
        }
        // Set text after color change
        holder.binding.amount.setText(amountWithType);

        // Set onclick listener to each item
        holder.itemView.setOnClickListener(view -> {

            if (currentTransaction.getTransactionStatus() == 0) {
                mFragment.togglePayslipPopup(view);
            }
            else {
                if (holder.binding.linearLayoutExtraInfo.getVisibility() == View.GONE) {
                    // it's collapsed - expand it
                    holder.binding.linearLayoutExtraInfo.setVisibility(View.VISIBLE);
                    //mDescriptionImg.setImageResource(R.drawable.ic_expand_less_black_24dp);
                } else {
                    // it's expanded - collapse it
                    holder.binding.linearLayoutExtraInfo.setVisibility(View.GONE);
                    //mDescriptionImg.setImageResource(R.drawable.ic_expand_more_black_24dp);
                }
                //animation here
            }
        });
    }

    @Override
    public int getItemCount() {
        return transactions.size();
    }

    //Binding
    static class TxViewHolder extends RecyclerView.ViewHolder
    {
        private static TxViewHolder create(LayoutInflater inflater,ViewGroup parent)
        {
            TransactionCardBinding transactionCardBinding = TransactionCardBinding
                    .inflate(inflater,parent,false);
            return new TxViewHolder(transactionCardBinding);
        }

        final TransactionCardBinding binding;

        private TxViewHolder(TransactionCardBinding binding)
        {
            super(binding.getRoot());
            this.binding = binding;
        }

        private void onBind(TxEntity txEntity) {
            binding.setTx(txEntity);
            binding.executePendingBindings();
        }
    }

}
