package com.example.tjombol.views;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tjombol.Adapters.UpcomingAdapter;
import com.example.tjombol.databinding.FragmentUpcomingSalaryBinding;
import com.example.tjombol.R;
import com.example.tjombol.db.TxEntity;
import com.example.tjombol.remote.Resource;
import com.example.tjombol.remote.Status;
import com.example.tjombol.viewModels.TransactionListViewModel;
import com.example.tjombol.views.Base.BaseFragment;

import java.util.List;

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
        dataBinding = DataBindingUtil.inflate(inflater, getLayoutRes(), container, false);

        Log.d(TAG, "onCreateView: Upcoming Fragment");
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        //layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        dataBinding.upComingRecyclerView.setLayoutManager(layoutManager);
        dataBinding.upComingRecyclerView.addItemDecoration(new LineDividerRecyclerViewDark(getActivity()));

        UpcomingAdapter upcomingAdapter = new UpcomingAdapter();
        dataBinding.upComingRecyclerView.setAdapter(upcomingAdapter);
        //resource
        return dataBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel.getTransactionsObservable().observe(getViewLifecycleOwner(), listResource ->  {
            if(null != listResource && (listResource.status == Status.ERROR || listResource.status == Status.SUCCESS)){
                dataBinding.progressBarLoading.setVisibility(View.GONE);
            }
            dataBinding.setResource(listResource);
            if(null != dataBinding.upComingRecyclerView.getAdapter() && dataBinding.upComingRecyclerView.getAdapter().getItemCount() > 0){
                //dataBinding.errorText.setVisibility(View.GONE);
            }
        });
    }
}
