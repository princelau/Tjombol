package com.example.tjombol.Views;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tjombol.Models.TransactionModel;
import com.example.tjombol.R;
import com.example.tjombol.ViewModels.TransactionViewModel;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

//First to be implement
public class TransactionFragment extends Fragment {

    //@Inject
    //ViewModelFactory viewModelFactory;

    @BindView(R.id.textViewEmpty)
    TextView mTextViewEmpty;

    @BindView(R.id.progressBarLoading)
    ProgressBar mProgressBarLoading;

    @BindView(R.id.imageViewEmpty)
    ImageView mImageViewEmpty;

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;

    @BindView(R.id.payslipPopupBackButton)
    Button backButton;

    private Unbinder unbinder;

    private ListAdapter mListadapter;

    private TransactionViewModel transactionViewModel;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_transactions, container, false);
        unbinder = ButterKnife.bind(this, view);

        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.addItemDecoration(new LineDividerRecyclerView(getActivity()));

        transactionViewModel = ViewModelProviders.of(this).get(TransactionViewModel.class);

        //Live data is lifecycle aware, and it will only update our activity if it is in foreground
        transactionViewModel.getProjectRetroListObservable().observe(this, new Observer<List<TransactionModel>>() {
            @Override
            public void onChanged(List<TransactionModel> transactionModels) {
                //update recycler view
                Toast.makeText(getContext(),"on Changed",Toast.LENGTH_SHORT).show();
            }
        });
        //

        /*
        ArrayList data = new ArrayList<TransactionModel>();
        for (int i = 0; i < TransactionInformation.idArray.length; i++)
        {
            data.add(
                    new Transaction
                            (
                                    TransactionInformation.idArray[i],
                                    TransactionInformation.senderArray[i],
                                    TransactionInformation.receiverArray[i],
                                    TransactionInformation.typeArray[i],
                                    TransactionInformation.amountArray[i],
                                    TransactionInformation.dateArray[i],
                                    TransactionInformation.commentArray[i],
                                    TransactionInformation.statusArray[i]
                            ));
        }

        mListadapter = new ListAdapter(data);
        mRecyclerView.setAdapter(mListadapter);
        */
        return view;
    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    /*
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
    */

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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}