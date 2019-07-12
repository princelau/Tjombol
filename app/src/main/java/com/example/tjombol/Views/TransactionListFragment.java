package com.example.tjombol.Views;



import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tjombol.Adapters.TransactionAdapter;
import com.example.tjombol.R;
import com.example.tjombol.ViewModels.TransactionListViewModel;
import com.example.tjombol.Views.Base.BaseFragment;
import com.example.tjombol.databinding.FragmentTransactionsBinding;
import com.example.tjombol.remote.Status;

import java.util.Objects;

import javax.inject.Inject;

import butterknife.OnClick;
import dagger.android.support.AndroidSupportInjection;

//First to be implement
//public class TransactionListFragment extends BaseFragment<TransactionListViewModel, FragmentTransactionsBinding> {
//
public class TransactionListFragment extends BaseFragment<TransactionListViewModel,FragmentTransactionsBinding>{
    //
    private PopupWindow popUp = null;

    //@Override
    protected Class<TransactionListViewModel> getViewModel() {
        return TransactionListViewModel.class;
    }

    //@Override
    protected int getLayoutRes() {
        return R.layout.fragment_transactions;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        dataBinding = DataBindingUtil.inflate(inflater, getLayoutRes(), container, false);
        // Set Recycler View
        //final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        // Setup Adapter
        TransactionAdapter transactionAdapter = new TransactionAdapter(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(RecyclerView.VERTICAL);


        dataBinding.recyclerView.setLayoutManager(layoutManager);
        dataBinding.recyclerView.addItemDecoration(new LineDividerRecyclerView(getActivity()));
        dataBinding.recyclerView.setAdapter(transactionAdapter);

        //
        viewModel.getTransactionsObservable()
                .observe(this, listResource ->  {
                    if(null != listResource && (listResource.status == Status.ERROR || listResource.status == Status.SUCCESS)){
                        dataBinding.progressBarLoading.setVisibility(View.GONE);
                    }
                    //transactionAdapter.setTransactions(listResource.data);
                    //Log.d("TxListFrag", "onCreateView: "+listResource.data);
                    dataBinding.setResource(listResource);
                    // If the cached data is already showing then no need to show the error
                    if(null != dataBinding.recyclerView.getAdapter() && dataBinding.recyclerView.getAdapter().getItemCount() > 0){
                        dataBinding.errorText.setVisibility(View.GONE);
                    }
                });
        return dataBinding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Live data is lifecycle aware, and it will only update our activity if it is in foreground


    }
    /**
    public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder>
    {
        private ArrayList<Transaction> transactionList;

        public ListAdapter(ArrayList<Transaction> transactionsList)
        {
            this.transactionList = transactionsList;
        }

        public class ViewHolder extends RecyclerView.ViewHolder
        {
            TextView textViewId;
            TextView textViewSender;
            TextView textViewReceiver;
            TextView textViewType;
            TextView textViewAmount;
            TextView textViewDate;
            TextView textViewComment;
            TextView textViewStatus;
            LinearLayout linearLayoutExtraInfo;


            public ViewHolder(View itemView)
            {
                super(itemView);
                this.textViewId = (TextView) itemView.findViewById(R.id.id);
                this.textViewSender = itemView.findViewById(R.id.sender);
                //this.textViewReceiver = itemView.findViewById(R.id.receiver);
                //this.textViewType = itemView.findViewById(R.id.type);
                this.textViewAmount = (TextView) itemView.findViewById(R.id.amount);
                this.textViewDate = (TextView) itemView.findViewById(R.id.date);
                this.textViewComment = itemView.findViewById(R.id.comment);
                this.textViewStatus = itemView.findViewById(R.id.status);
                this.linearLayoutExtraInfo = itemView.findViewById(R.id.linearLayoutExtraInfo);
            }
        }

        @Override
        public ListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
        {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.transaction_card, parent, false);


            ViewHolder viewHolder = new ViewHolder(view);





            return viewHolder;
        }

        @Override
        public void onBindViewHolder(final ListAdapter.ViewHolder holder, final int position)
        {
            String amountWithType="";
            if (transactionList.get(position).getType().equals("INCOMING")) {
                //amountWithType = "<font color=#2ECC40>+$"+Integer.toString(transactionList.get(position).getAmount())+"</font>";
                holder.textViewAmount.setTextColor(ContextCompat.getColor(getActivity(),R.color.colorGreen));
                amountWithType = "+$"+Integer.toString(transactionList.get(position).getAmount());
            }
            else if (transactionList.get(position).getType().equals("OUTGOING")) {
                holder.textViewAmount.setTextColor(ContextCompat.getColor(getActivity(),R.color.colorRed));
                amountWithType = "-$"+Integer.toString(transactionList.get(position).getAmount());
            }
            holder.textViewId.setText(transactionList.get(position).getId());
            holder.textViewSender.setText(transactionList.get(position).getSender());
            //holder.textViewReceiver.setText(transactionList.get(position).getReceiver());
            //holder.textViewType.setText(transactionList.get(position).getType());
            holder.textViewAmount.setText(amountWithType);
            holder.textViewDate.setText(transactionList.get(position).getDate());
            holder.textViewComment.setText(transactionList.get(position).getComment());
            holder.textViewStatus.setText(transactionList.get(position).getStatus());


            holder.itemView.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {
                    //Toast.makeText(getActivity(), "Item " + position + " is clicked.", Toast.LENGTH_SHORT).show();

                    if (transactionList.get(position).getType().equals("OUTGOING")) {

                        togglePayslipPopup(view);
                    }
                    else {

                        if (holder.linearLayoutExtraInfo.getVisibility() == View.GONE) {
                            // it's collapsed - expand it
                            holder.linearLayoutExtraInfo.setVisibility(View.VISIBLE);
                            //mDescriptionImg.setImageResource(R.drawable.ic_expand_less_black_24dp);
                        } else {
                            // it's expanded - collapse it
                            holder.linearLayoutExtraInfo.setVisibility(View.GONE);
                            //mDescriptionImg.setImageResource(R.drawable.ic_expand_more_black_24dp);
                        }

                        //animation here
                    }
                }
            });

        }



        @Override
        public int getItemCount()
        {
            return transactionList.size();
        }


    }



    public void togglePayslipPopup(View anchorView) {

        DisplayMetrics dm = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels - dm.widthPixels/20;
        int height = dm.heightPixels - dm.heightPixels/7;

        View viewGroup= getActivity().getLayoutInflater().inflate(R.layout.payslip_popup, null, false);

        final PopupWindow popupWindow = new PopupWindow(viewGroup, width, height);
        //backButton = (Button) viewGroup.findViewById(R.id.payslipPopupBackButton);

        if (popupWindow.isShowing()) {
            backButton.setOnClickListener(null);
            popupWindow.dismiss();
        }
        else {
            popupWindow.setFocusable(true);
            popupWindow.setBackgroundDrawable(new ColorDrawable());
            popupWindow.setOutsideTouchable(true);
            popupWindow.showAtLocation(anchorView, Gravity.TOP, 0, 90);

            backButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    popupWindow.dismiss();
                }
            });

        }

    }
    */
    public void togglePayslipPopup(View anchorView) {

        DisplayMetrics dm = new DisplayMetrics();
        Objects.requireNonNull(getActivity()).getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels - dm.widthPixels/20;
        int height = dm.heightPixels - dm.heightPixels/7;

        View viewGroup= getActivity().getLayoutInflater().inflate(R.layout.payslip_popup, null, false);

        final PopupWindow popupWindow = new PopupWindow(viewGroup, width, height);
        //backButton = (Button) viewGroup.findViewById(R.id.payslipPopupBackButton);
        boolean popUpIsShowing = popupWindow.isShowing();

        if (popUpIsShowing) {
            //backButton.setOnClickListener(null);
            popupWindow.dismiss();
            popUpIsShowing = false;
            popUp = null;
        }
        else {
            popupWindow.setFocusable(true);
            popupWindow.setBackgroundDrawable(new ColorDrawable());
            popupWindow.setOutsideTouchable(true);
            popupWindow.showAtLocation(anchorView, Gravity.TOP, 0, 90);
            //closePopUp(popupWindow);
            popUp = popupWindow;
        }
    }

    @OnClick(R.id.payslipPopupBackButton)
    void closePopUp() {
        if (popUp != null) {
            popUp.dismiss();

        }
    }
}