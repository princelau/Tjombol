package com.example.tjombol.views;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.ebanx.swipebtn.OnActiveListener;
import com.ebanx.swipebtn.SwipeButton;
import com.example.tjombol.R;
import com.example.tjombol.viewModels.ClaimSalaryViewModel;

public class ClaimSalaryFragment extends Fragment {

    SharedPreferences sharedPreferences;
    Button claimSalaryBackButton;
    SwipeButton claimSalaryButton;
    private static final String TAG = "Login";

    String amountWithdrawString;
    int amountWithdraw;
    private int prev_balance;

    TextView generatedAmountRequested;

    private ClaimSalaryViewModel claimSalaryViewModel;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_claim_salary, container, false);

        sharedPreferences = getContext().getSharedPreferences("user_data",
                Context.MODE_PRIVATE);
        prev_balance =  Integer.valueOf(sharedPreferences.getString("mSalary", ""));

        Bundle bundle = this.getArguments();
        if(bundle != null){
            amountWithdrawString = String.valueOf(bundle.get("amountWithdraw"));
        }
        amountWithdraw = Integer.valueOf(amountWithdrawString);

        /// ViewModel
        claimSalaryViewModel = ViewModelProviders.of(this).get(ClaimSalaryViewModel.class);
        claimSalaryViewModel.getLiveMsg().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Log.d(TAG, "onChanged: ");
                showToast(s);
                sharedPreferences.edit().putString("wBalance",String.valueOf(prev_balance-amountWithdraw)).apply();
            }
        });

        generatedAmountRequested = view.findViewById(R.id.generatedAmountRequested);
        generatedAmountRequested.setText(amountWithdrawString);

        claimSalaryBackButton = view.findViewById(R.id.claimSalaryBackButton);
        claimSalaryBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                HomeFragment homeFragment = new HomeFragment();
                fragmentTransaction.replace(R.id.fragment_container, homeFragment);
                fragmentTransaction.commit();

            }
        });

        claimSalaryButton = (SwipeButton) view.findViewById(R.id.claimSalarySwipeButton);
        claimSalaryButton.setOnActiveListener(new OnActiveListener() {
            @Override
            public void onActive() {

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //
                        claimSalaryViewModel.getTransactionFeedBack("8498293",String.valueOf(amountWithdraw),"daily pay");

                        // transfer scene
                        FragmentManager fragmentManager = getFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        HomeFragment homeFragment = new HomeFragment();
                        fragmentTransaction.replace(R.id.fragment_container, homeFragment);
                        fragmentTransaction.commit();
                    }
                }, 1500);


            }
        });
        return view;


    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


    }

    void showToast(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_LONG).show();
    }
}
