package com.example.tjombol;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class SignupStep4Fragment extends Fragment {


    Button backButtonSignup4;
    Button createAccountButton;

    EditText companyPasswordSignupEditText;
    EditText monthlySalarySignupEditText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.fragment_signup_step4, container, false);

        companyPasswordSignupEditText = view.findViewById(R.id.companyPasswordSignupEditText);
        monthlySalarySignupEditText = view.findViewById(R.id.monthlySalaryEditText);

        backButtonSignup4 = view.findViewById(R.id.backButtonSignup4);

        backButtonSignup4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewPager vp = getActivity().findViewById(R.id.nonSwipeViewPager);
                vp.setCurrentItem(vp.getCurrentItem()-1);
            }
        });

        createAccountButton = view.findViewById(R.id.createAccountButton);
        createAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkDataEntered()) {
                    String[] dataArray = new String[] {monthlySalarySignupEditText.getText().toString()};
                    dataPasser.onDataPass4(dataArray);
                    Intent intent = new Intent(getActivity(), MainActivity.class);
                    startActivity(intent);
                }
            }
        });

        return view;
    }

    OnDataPass4 dataPasser;

    public interface OnDataPass4 {
        public void onDataPass4(String[] data);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        dataPasser = (OnDataPass4) context;
    }


    boolean isEmpty(EditText text) {
        CharSequence str = text.getText().toString();
        return TextUtils.isEmpty(str);
    }


    boolean isDigits(EditText text) {
        String digitString = text.getText().toString();
        return (digitString.matches("[0-9]+") && !isEmpty(text));
    }

    boolean checkDataEntered() {
        boolean ableToLogIn = true;

        if (!isDigits(companyPasswordSignupEditText)) {
            companyPasswordSignupEditText.setError("Please enter a valid company password");
            ableToLogIn = false;
        }

        if (!isDigits(monthlySalarySignupEditText)) {
            monthlySalarySignupEditText.setError("Please enter a valid monthly salary!");
            ableToLogIn = false;
        }


        return ableToLogIn;
    }
}

