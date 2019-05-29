package com.example.tjombol;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class SignupStep3Fragment extends Fragment {

    Button signup3Button;
    Button backButtonSignup3;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.fragment_signup_step3, container, false);


        signup3Button = view.findViewById(R.id.signup3Button);
        signup3Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewPager vp = getActivity().findViewById(R.id.nonSwipeViewPager);
                vp.setCurrentItem(vp.getCurrentItem()+1);
                //String[] dataArray = new String[] {paymentScheme.getText().toString()};
                //dataPasser.onDataPass3(dataArray);
            }
        });

        backButtonSignup3 = view.findViewById(R.id.backButtonSignup3);
        backButtonSignup3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewPager vp = getActivity().findViewById(R.id.nonSwipeViewPager);
                vp.setCurrentItem(vp.getCurrentItem()-1);
            }
        });

        return view;
    }


    OnDataPass3 dataPasser;

    public interface OnDataPass3 {
        public void onDataPass3(String[] data);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        dataPasser = (OnDataPass3) context;
    }



}

