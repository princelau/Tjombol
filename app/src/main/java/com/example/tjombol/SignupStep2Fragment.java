package com.example.tjombol;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class SignupStep2Fragment extends Fragment {


    Button signup2Button;
    Button backButtonSignup2;
    EditText mobileNumberSignupEditText;
    EditText nricSignupEditText;
    EditText bankNumberSignupEditText;
    EditText companyIdSignupEditText;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.fragment_signup_step2, container, false);

        mobileNumberSignupEditText = view.findViewById(R.id.mobileNumberSignupEditText);
        nricSignupEditText = view.findViewById(R.id.nricSignupEditText);
        bankNumberSignupEditText = view.findViewById(R.id.bankNumberSignupEditText);
        companyIdSignupEditText = view.findViewById(R.id.bankNumberSignupEditText);

        signup2Button = view.findViewById(R.id.signup2Button);
        signup2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewPager vp = getActivity().findViewById(R.id.nonSwipeViewPager);
                vp.setCurrentItem(vp.getCurrentItem()+1);
            }
        });

        backButtonSignup2 = view.findViewById(R.id.backButtonSignup2);
        backButtonSignup2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewPager vp = getActivity().findViewById(R.id.nonSwipeViewPager);
                vp.setCurrentItem(vp.getCurrentItem()-1);
            }
        });




        return view;
    }
}

