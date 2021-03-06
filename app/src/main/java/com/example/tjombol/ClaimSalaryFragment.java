package com.example.tjombol;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.ebanx.swipebtn.OnActiveListener;
import com.ebanx.swipebtn.SwipeButton;

public class ClaimSalaryFragment extends Fragment {

    Button claimSalaryBackButton;
    SwipeButton claimSalaryButton;

    String amountWithdrawString;
    int amountWithdraw;

    TextView generatedAmountRequested;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_claim_salary, container, false);

        Bundle b = this.getArguments();
        if(b != null){
            amountWithdrawString = String.valueOf(b.get("amountWithdraw"));
        }

        amountWithdraw = Integer.valueOf(amountWithdrawString);

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


}
