package com.example.tjombol.views;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.tjombol.Adapters.UpcomingAdapter;
import com.example.tjombol.R;
import com.example.tjombol.viewModels.HomeFragmentViewModel;

public class RecentTransactionsFragment extends Fragment {

    private static final String TAG = "Recent_Fragment";
    private TextView mTextViewEmpty;
    private ProgressBar mProgressBarLoading;
    private ImageView mImageViewEmpty;
    private RecyclerView mRecyclerView;
    private ListAdapter mListadapter;
    private HomeFragmentViewModel homeFragmentViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_recent_transactions, container, false);
        Log.d(TAG, "onCreateView: Recent Fragment");

        mRecyclerView = (RecyclerView) view.findViewById(R.id.fragRecentRecyclerView);
        mTextViewEmpty = (TextView)view.findViewById(R.id.textViewEmpty);
        mImageViewEmpty = (ImageView)view.findViewById(R.id.imageViewEmpty);
        mProgressBarLoading = (ProgressBar)view.findViewById(R.id.progressBarLoading);

        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.addItemDecoration(new LineDividerRecyclerViewDark(getActivity()));

        // ViewModel
        UpcomingAdapter upcomingAdapter = new UpcomingAdapter();
        mRecyclerView.setAdapter(upcomingAdapter);
        /*
        homeFragmentViewModel = ViewModelProviders.of(this).get(HomeFragmentViewModel.class);
        homeFragmentViewModel.init();
        homeFragmentViewModel.getUserInfoObservable("7111111").observe(this, new Observer<Resource<UserInfoResponseModel>>() {
            @Override
            public void onChanged(Resource<UserInfoResponseModel> userInfoResponseModelResource) {
                userInfoResponseModelResource.data
            }
        });
        */

        /*
        ArrayList data = new ArrayList<Transaction>();
        for (int i = 0; i < TransactionInformation.idArray.length; i++)


        for (int i = 0; i < 3; i++)
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


            public ViewHolder(View itemView)
            {
                super(itemView);
                //this.textViewId = (TextView) itemView.findViewById(R.id.id);
                this.textViewSender = itemView.findViewById(R.id.sender);
                //this.textViewReceiver = itemView.findViewById(R.id.receiver);
                //this.textViewType = itemView.findViewById(R.id.type);
                this.textViewAmount = (TextView) itemView.findViewById(R.id.amount);
                this.textViewDate = (TextView) itemView.findViewById(R.id.date);
                //this.textViewComment = itemView.findViewById(R.id.comment);
                //this.textViewStatus = itemView.findViewById(R.id.status);
                //this.linearLayoutExtraInfo = itemView.findViewById(R.id.linearLayoutExtraInfo);
            }
        }

        @Override
        public ListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
        {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.transaction_card_small, parent, false);

            ViewHolder viewHolder = new ListAdapter.ViewHolder(view);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(final ListAdapter.ViewHolder holder, final int position)
        {
            String amountWithType="";
            if (transactionList.get(position).getType().equals("INCOMING")) {
                holder.textViewAmount.setTextColor(ContextCompat.getColor(getActivity(),R.color.colorGreen));
                amountWithType = "+$"+Integer.toString(transactionList.get(position).getAmount());
            }
            else if (transactionList.get(position).getType().equals("OUTGOING")) {
                holder.textViewAmount.setTextColor(ContextCompat.getColor(getActivity(),R.color.colorRed));
                amountWithType = "-$"+Integer.toString(transactionList.get(position).getAmount());
            }
            //holder.textViewId.setText(transactionList.get(position).getId());
            holder.textViewSender.setText(transactionList.get(position).getSender());
            //holder.textViewReceiver.setText(transactionList.get(position).getReceiver());
            //holder.textViewType.setText(transactionList.get(position).getType());
            holder.textViewAmount.setText(amountWithType);
            holder.textViewDate.setText(transactionList.get(position).getDate());
            //holder.textViewComment.setText(transactionList.get(position).getComment());
            //holder.textViewStatus.setText(transactionList.get(position).getStatus());


            holder.itemView.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    Toast.makeText(getActivity(), "Item " + position + " is clicked.", Toast.LENGTH_SHORT).show();
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
}
