package com.example.tjombol.views;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tjombol.Adapters.TransactionAdapter;
import com.example.tjombol.R;
import com.example.tjombol.db.TxEntity;
import com.example.tjombol.remote.UserConstant;
import com.example.tjombol.viewModels.TransactionListViewModel;
import com.example.tjombol.views.Base.BaseFragment;
import com.example.tjombol.databinding.FragmentTransactionsBinding;
import com.example.tjombol.remote.Status;

import java.util.Locale;
import java.util.Objects;


public class TransactionListFragment extends BaseFragment<TransactionListViewModel, FragmentTransactionsBinding> {
    private UserConstant userConstant;
    private static final String TAG = "TxListFragment";
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

        // Setup Adapter
        TransactionAdapter transactionAdapter = new TransactionAdapter(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(RecyclerView.VERTICAL);

        dataBinding.recyclerView.setLayoutManager(layoutManager);
        dataBinding.recyclerView.addItemDecoration(new LineDividerRecyclerView(getActivity()));
        dataBinding.recyclerView.setAdapter(transactionAdapter);
        // butterKnife binding
        userConstant = UserConstant.getInstance();
        //
        viewModel.getTransactionsObservable()
                .observe(this, listResource ->  {
                    if(null != listResource && (listResource.status == Status.ERROR || listResource.status == Status.SUCCESS)){
                        dataBinding.progressBarLoading.setVisibility(View.GONE);
                    }
                    dataBinding.setResource(listResource);
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

    public void togglePayslipPopup(View anchorView, TxEntity currentTx) {

        //ButterKnife.bind(anchorView);
        WindowManager wm = (WindowManager) anchorView.getContext().getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels - dm.widthPixels/20;
        //int height = dm.heightPixels - dm.heightPixels/7;
        //PayslipPopupBinding PopupBinding = DataBindingUtil.inflate(LayoutInflater.from(getContext()),R.layout.payslip_popup,null,false);
        View viewGroup= Objects.requireNonNull(getActivity()).getLayoutInflater().inflate(R.layout.payslip_popup, null, false);
        //Binding view components
        TextView paySlipDate = viewGroup.findViewById(R.id.paySlipDatePaySlip);
        TextView txId = viewGroup.findViewById(R.id.transactionIdPaySlip);
        TextView name = viewGroup.findViewById(R.id.employeeNamePaySlip);
        TextView eId = viewGroup.findViewById(R.id.employeeIdPaySlip);
        TextView rAccount = viewGroup.findViewById(R.id.bankAccountPaySlip);
        TextView mSalary = viewGroup.findViewById(R.id.eMonthlySalaryPaySlip);
        TextView companyName = viewGroup.findViewById(R.id.companyNamePaySlip);
        TextView txAmount = viewGroup.findViewById(R.id.totalSalaryPaySlip);
        TextView txFee = viewGroup.findViewById(R.id.transactionFeePaySlip);
        //Insert Data
        paySlipDate.setText(currentTx.getDate().replace(".000Z","").replace("T"," "));
        txId.setText(String.format("#%s",currentTx.getTransactionId()));
        name.setText(currentTx.getReceiver());
        eId.setText(userConstant.getUser_eId());
        rAccount.setText(userConstant.getUser_account());
        mSalary.setText(userConstant.getUser_salary());
        companyName.setText(userConstant.getCompany_Name());
        txAmount.setText(String.format("$ %s",currentTx.getAmount()));
        txFee.setText(String.format("$ %s", String.valueOf(Double.valueOf(currentTx.getAmount())*Double.valueOf(userConstant.getUser_rate()))));
        //UI Setting
        final PopupWindow popupWindow = new PopupWindow(viewGroup, width, ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setFocusable(true);
        popupWindow.setBackgroundDrawable(new ColorDrawable());
        popupWindow.setOutsideTouchable(true);
        popupWindow.showAtLocation(anchorView, Gravity.TOP, 0, 150);
    }


}