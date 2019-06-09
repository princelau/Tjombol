package com.example.tjombol.Views;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.tjombol.R;

public class SignupStep1Fragment extends Fragment {

    EditText nameEditText;
    EditText emailEditText;
    EditText passwordEditText;
    Button signupButton;
    Button goToLoginButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.fragment_signup_step1, container, false);

        goToLoginButton = view.findViewById(R.id.goToLoginButton);
        goToLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Login.class);
                startActivity(intent);
            }
        });


        signupButton  = view.findViewById(R.id.signupButton);
        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewPager vp = getActivity().findViewById(R.id.nonSwipeViewPager);
                vp.setCurrentItem(vp.getCurrentItem()+1);
            }
        });

        String text = "<font color=#ffffff>Already have an account? </font> <font color=#67d6d3>Log In</font>";
        goToLoginButton.setText(Html.fromHtml(text));


        nameEditText = view.findViewById(R.id.nameSignupEditText);
        emailEditText = view.findViewById(R.id.emailSignupEditText);
        passwordEditText = view.findViewById(R.id.passwordSignupEditText);

        return view;
    }
}

