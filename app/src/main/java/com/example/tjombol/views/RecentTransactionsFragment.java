package com.example.tjombol.views;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.tjombol.Adapters.RecentAdapter;
import com.example.tjombol.Adapters.UpcomingAdapter;
import com.example.tjombol.R;
import com.example.tjombol.databinding.FragmentRecentTransactionsBinding;
import com.example.tjombol.remote.Status;
import com.example.tjombol.viewModels.HomeFragmentViewModel;
import com.example.tjombol.viewModels.TransactionListViewModel;
import com.example.tjombol.views.Base.BaseFragment;

public class RecentTransactionsFragment extends BaseFragment<TransactionListViewModel, FragmentRecentTransactionsBinding> {

    private static final String TAG = "Recent_Fragment";

    @Override
    protected Class<TransactionListViewModel> getViewModel() {
        return TransactionListViewModel.class;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_recent_transactions;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        Log.d(TAG, "onCreateView: Recent Fragment");
        dataBinding = DataBindingUtil.inflate(inflater, getLayoutRes(), container, false);

        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        dataBinding.fragRecentRecyclerView.setLayoutManager(layoutManager);
        dataBinding.fragRecentRecyclerView.addItemDecoration(new LineDividerRecyclerViewDark(getActivity()));

        // ViewModel
        RecentAdapter recentAdapter = new RecentAdapter();
        dataBinding.fragRecentRecyclerView.setAdapter(recentAdapter);
        return dataBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel.getTransactionsObservable().observe(getViewLifecycleOwner(), listResource ->  {
            if(null != listResource && (listResource.status == Status.ERROR || listResource.status == Status.SUCCESS)){
                dataBinding.progressBarLoading.setVisibility(View.GONE);
            }
            dataBinding.setResource(listResource);
            if(null != dataBinding.fragRecentRecyclerView.getAdapter() && dataBinding.fragRecentRecyclerView.getAdapter().getItemCount() > 0){
                //dataBinding.errorText.setVisibility(View.GONE);
            }
        });
    }
}
