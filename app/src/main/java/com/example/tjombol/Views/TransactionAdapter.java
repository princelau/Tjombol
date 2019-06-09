package com.example.tjombol.Views;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tjombol.Models.TransactionModel;
import com.example.tjombol.R;

import java.util.ArrayList;
import java.util.List;

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.TxViewHolder> {

    private List<TransactionModel> transactions = new ArrayList<>();

    class TxViewHolder extends RecyclerView.ViewHolder
    {
        private TextView textViewId;
        private TextView textViewSender;
        private TextView textViewAmount;
        private TextView textViewDate;
        private TextView textViewComment;
        private TextView textViewStatus;
        private LinearLayout linearLayoutExtraInfo;

        private TxViewHolder(View itemView)
        {
            super(itemView);
            textViewId = itemView.findViewById(R.id.id);
            textViewSender = itemView.findViewById(R.id.sender);
            textViewAmount = itemView.findViewById(R.id.amount);
            textViewDate = itemView.findViewById(R.id.date);
            textViewComment = itemView.findViewById(R.id.comment);
            textViewStatus = itemView.findViewById(R.id.status);
            linearLayoutExtraInfo = itemView.findViewById(R.id.linearLayoutExtraInfo);
        }
    }

    @NonNull
    @Override
    public TransactionAdapter.TxViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.transaction_card, parent, false);
        return new TxViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final TransactionAdapter.TxViewHolder holder, int position) {

        TransactionModel currentTransaction = transactions.get(position);

        String amountWithType;

        //Set Text Color
        if (currentTransaction.getType().equals("INCOMING")) {
            //amountWithType = "<font color=#2ECC40>+$"+Integer.toString(transactionList.get(position).getAmount())+"</font>";
            holder.textViewAmount.setTextColor(Color.parseColor("#47AD51"));
            amountWithType = "+$"+Integer.toString(currentTransaction.getAmount());
        }
        else if (currentTransaction.getType().equals("OUTGOING")) {
            holder.textViewAmount.setTextColor(Color.parseColor("#E03F35"));
            amountWithType = "-$"+Integer.toString(currentTransaction.getAmount());
        }
        else{
            holder.textViewAmount.setTextColor(Color.parseColor("#FF9800"));
            amountWithType = "-$"+Integer.toString(currentTransaction.getAmount());
        }

        //Write Text
        holder.textViewId.setText(currentTransaction.getTransactionId());
        holder.textViewSender.setText(currentTransaction.getSender());
        holder.textViewAmount.setText(amountWithType);
        holder.textViewDate.setText(currentTransaction.getDate());
        holder.textViewComment.setText(currentTransaction.getComment());
        holder.textViewStatus.setText(currentTransaction.getTransactionStatus());
    }

    @Override
    public int getItemCount() {
        return transactions.size();
    }

    // notify changes
    public void setTransactions(List<TransactionModel> transactions){
        this.transactions = transactions;
        notifyDataSetChanged();
    }


}
