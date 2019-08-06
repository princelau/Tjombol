package com.example.tjombol.views;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tjombol.Adapters.UpcomingAdapter;
import com.example.tjombol.databinding.FragmentUpcomingSalaryBinding;
import com.example.tjombol.R;
import com.example.tjombol.remote.Status;
import com.example.tjombol.viewModels.TransactionListViewModel;
import com.example.tjombol.views.Base.BaseFragment;

public class UpcomingSalaryFragment extends BaseFragment<TransactionListViewModel, FragmentUpcomingSalaryBinding> {

    private static final String TAG = "Upcoming_Fragment";

    @Override
    protected Class<TransactionListViewModel> getViewModel() {
        return TransactionListViewModel.class;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_upcoming_salary;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //return inflater.inflate(R.layout.fragment_upcoming_salary, container, false);
        dataBinding = DataBindingUtil.inflate(inflater, getLayoutRes(), container, false);

        Log.d(TAG, "onCreateView: Upcoming Fragment");
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        //layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        dataBinding.upComingRecyclerView.setLayoutManager(layoutManager);
        dataBinding.upComingRecyclerView.addItemDecoration(new LineDividerRecyclerViewDark(getActivity()));

        UpcomingAdapter upcomingAdapter = new UpcomingAdapter();
        dataBinding.upComingRecyclerView.setAdapter(upcomingAdapter);
        viewModel.getTransactionsObservable().observe(this, listResource ->  {
            if(null != listResource && (listResource.status == Status.ERROR || listResource.status == Status.SUCCESS)){
                dataBinding.progressBarLoading.setVisibility(View.GONE);
            }
            dataBinding.setResource(listResource);
            if(null != dataBinding.upComingRecyclerView.getAdapter() && dataBinding.upComingRecyclerView.getAdapter().getItemCount() > 0){
                //dataBinding.errorText.setVisibility(View.GONE);
            }
        });
        //List<TxEntity> txlist = database.transactionDao().getAllTxsNormal();

        /*
        ArrayList data = new ArrayList<Transaction>();
        //for (int i = 0; i < TransactionInformation.idArray.length; i++)
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
        //new loadDataAsyncTask(database,mbinding);
        return dataBinding.getRoot();
    }
    /*
    public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder>
    {
        private ArrayList<TxEntity> transactionList;

        public ListAdapter(ArrayList<TxEntity> transactionsList)
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
            if (transactionList.get(position).getTransactionStatus() == 3) {
                holder.textViewAmount.setTextColor(ContextCompat.getColor(getActivity(),R.color.colorGreen));
                amountWithType = "+$"+transactionList.get(position).getAmount();
            }
            else if (transactionList.get(position).getTransactionStatus() == 4) {
                holder.textViewAmount.setTextColor(ContextCompat.getColor(getActivity(),R.color.colorAccent));
                amountWithType = "-$"+transactionList.get(position).getAmount();
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


    }*/

}
