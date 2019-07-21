package com.example.tjombol.views;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.util.Patterns;
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

        nameEditText = view.findViewById(R.id.nameSignupEditText);
        emailEditText = view.findViewById(R.id.emailSignupEditText);
        passwordEditText = view.findViewById(R.id.passwordSignupEditText);

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
                if (checkDataEntered()) {
                    String[] dataArray = new String[] {nameEditText.getText().toString(),emailEditText.getText().toString(), passwordEditText.getText().toString()};
                    dataPasser.onDataPass(dataArray);
                    ViewPager vp = getActivity().findViewById(R.id.nonSwipeViewPager);
                    vp.setCurrentItem(vp.getCurrentItem() + 1);
                }

            }
        });

        String text = "<font color=#ffffff>Already have an account? </font> <font color=#67d6d3>Log In</font>";
        goToLoginButton.setText(Html.fromHtml(text));



        return view;
    }



    OnDataPass dataPasser;

    public interface OnDataPass {
        public void onDataPass(String[] data);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        dataPasser = (OnDataPass) context;
    }





    boolean isEmpty(EditText text) {
        CharSequence str = text.getText().toString();
        return TextUtils.isEmpty(str);
    }

    boolean isEmail(EditText text) {
        CharSequence email = text.getText().toString();
        return (!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());
    }

    boolean isValidPasswordLength(EditText text) {
        CharSequence str = text.getText().toString();
        return (str.length() > 7);
    }

    boolean checkDataEntered() {
        boolean ableToLogIn = true;

        if (isEmpty(nameEditText)) {
            nameEditText.setError("Please enter your name!");
            ableToLogIn = false;
        }


        if (!isEmail(emailEditText)) {
            emailEditText.setError("Enter a valid email!");
            ableToLogIn = false;
        }
        if (!isValidPasswordLength(passwordEditText)) {
            passwordEditText.setError("Password has to be at least 8 characters");
            ableToLogIn = false;
        }

        if (isEmpty(passwordEditText)) {
            passwordEditText.setError("Please enter a password");
            ableToLogIn = false;
        }
        return ableToLogIn;
    }

}

