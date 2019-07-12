package com.example.tjombol.Views;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tjombol.Adapters.TransactionAdapter;
import com.example.tjombol.R;
import com.example.tjombol.ViewModels.TransactionListViewModel;
import com.example.tjombol.Views.Base.BaseFragment;
import com.example.tjombol.databinding.FragmentTransactionsBinding;
import com.example.tjombol.remote.Status;

import java.util.Objects;

import butterknife.OnClick;

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

    public void togglePayslipPopup(View anchorView) {

        DisplayMetrics dm = new DisplayMetrics();
        Objects.requireNonNull(getActivity()).getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels - dm.widthPixels/20;
        int height = dm.heightPixels - dm.heightPixels/7;

        View viewGroup= getActivity().getLayoutInflater().inflate(R.layout.payslip_popup, null, false);

        final PopupWindow popupWindow = new PopupWindow(viewGroup, width, height);
        boolean popUpIsShowing = popupWindow.isShowing();

        if (popUpIsShowing) {
            popupWindow.dismiss();
            popUpIsShowing = false;
            popUp = null;
        }
        else {
            popupWindow.setFocusable(true);
            popupWindow.setBackgroundDrawable(new ColorDrawable());
            popupWindow.setOutsideTouchable(true);
            popupWindow.showAtLocation(anchorView, Gravity.TOP, 0, 90);
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