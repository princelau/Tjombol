package com.example.tjombol.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tjombol.R;
import com.example.tjombol.remote.Models.PlanModel;

import java.util.List;
/*
public class RecentTransactionsFragmentAdapter extends RecyclerView.Adapter<RecentTransactionsFragmentAdapter.RecentTransactionsFragmentAdapterVH> {

    Context mCtx;
    List<PlanModel> planModelList;

    public RecentTransactionsFragmentAdapter(Context mCtx, List<PlanModel> planModelList) {
        this.mCtx = mCtx;
        this.planModelList = planModelList;
    }
    @NonNull
    @Override
    public RecentTransactionsFragmentAdapterVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.id.fragRecentRecyclerView, parent, false);
        return new RecentTransactionsFragmentAdapterVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecentTransactionsFragmentAdapterVH holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class RecentTransactionsFragmentAdapterVH extends RecyclerView.ViewHolder {

        //ImageView imageView;
        //TextView textView;

        public RecentTransactionsFragmentAdapterVH(View itemView) {
            super(itemView);

            //imageView = itemView.findViewById(R.id.imageView);
            //textView = itemView.findViewById(R.id.textView);
        }
    }
}
*/